package com.cp.im.application.socket;

import cn.hutool.core.util.IdUtil;
import com.cp.im.domain.mq.Packet;
import com.cp.im.domain.repository.ChannelRepository;
import com.cp.im.infrastructure.OptionalHandler;
import com.cp.im.infrastructure.cmd.BasisOptCmd;
import com.cp.im.infrastructure.cmd.ChitchatCmd;
import com.cp.im.infrastructure.constants.SocketKeyConstants;
import com.cp.im.infrastructure.factory.HandlerContext;
import com.cp.im.infrastructure.group.SocketCtxGroup;
import com.cp.im.infrastructure.utils.RedisUtils;
import com.cp.im.utils.ExceptionUtil;
import com.cp.im.utils.StringUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.AttributeKey;
import java.time.Instant;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 连接处理.
 */
@Slf4j
@Component
public class MyInboundHandler extends SimpleChannelInboundHandler<Packet> {


  @Autowired
  private RedisUtils redisUtils;

  @Autowired
  private RedisTemplate<String, Object> redisTemplate;

  @Resource
  private HandlerContext handlerContext;

  private static MyInboundHandler execute;

  /**
   * 构造初始.
   */
  @PostConstruct
  public void init() {
    execute = this;
    execute.redisUtils = this.redisUtils;
    execute.redisTemplate = this.redisTemplate;
    execute.handlerContext = this.handlerContext;
  }

  /** 时间检查. */
  private static final int CHECK_HEART_BEAT_TIME = 50;

  /** 心跳标记. */
  private static final AttributeKey<Long> HEART_BEAT = AttributeKey.newInstance("HEART_BEAT");

  /** 工作组. */
  private static final EventLoopGroup WORK_GROUP = new NioEventLoopGroup(4);

  private static final String DEVICE_HEAD = "0x";

  /**
   * 注册管道.
   *
   * @param ctx 管道信息
   */
  @Override
  public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
    super.channelRegistered(ctx);
  }

  /**
   * 激活管道.
   *
   * @param ctx        管道信息
   * @throws Exception 异常信息
   */
  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    super.channelActive(ctx);
    SocketCtxGroup.group.add(ctx.channel());
    log.warn(">>>激活客户端={}", ctx.channel());
  }

  /**
   * 读取数据.
   *
   * @param ctx    管道信息
   * @param packet 数据包
   */
  @Override
  protected void channelRead0(ChannelHandlerContext ctx, Packet packet) {
    ctx.channel().attr(HEART_BEAT).set(Instant.now().toEpochMilli());
    WORK_GROUP.next().execute(() -> handler(ctx.channel(), packet));
  }

  /**
   * 数据分发.
   *
   * @param channel 管道信息
   * @param packet  数据包
   */
  private void handler(Channel channel, Packet packet) {
    try {
      OptionalHandler instance = execute.handlerContext.getInstance(packet.channel);
      if (Objects.nonNull(instance)) {
        log.warn(">>>Read={}", packet);
        instance.handle(channel, packet);
        return;
      }
      log.warn(">>>未知指令={}", packet);
      channel.writeAndFlush(packet);
    } catch (Exception e) {
      log.error(e.getMessage());
      log.error(ExceptionUtil.getStackTrace(e));
    }
  }

  /**
   * 读取完成.
   *
   * @param ctx 管道信息
   */
  @Override
  public void channelReadComplete(ChannelHandlerContext ctx) {
    ctx.flush();
  }

  /**
   * 解除注册.
   *
   * @param ctx        管道信息
   * @throws Exception 连接异常
   */
  @Override
  public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
    super.channelUnregistered(ctx);
  }

  /**
   * 移除设备.
   *
   * @param ctx        管道信息
   * @throws Exception 移除异常
   */
  @Override
  public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
    super.handlerRemoved(ctx);
    SocketCtxGroup.group.remove(ctx.channel());
  }

  /**
   * 注销管道.
   *
   * @param ctx        管道信息
   * @throws Exception 注销异常
   */
  @Override
  public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    super.channelInactive(ctx);
    String uuid = IdUtil.simpleUUID();
    String lockKey = "KEY_LOCK:SOCKET:" + DEVICE_HEAD + ctx.channel().id().asShortText();
    try {
      // 使用Redis加锁
      Boolean ifAbsent = execute.redisTemplate.opsForValue().setIfAbsent(lockKey, uuid, 90, TimeUnit.SECONDS);
      if (!ifAbsent) {
        log.warn(">>>执行注销管道加锁状态, lock_key=[{}]", lockKey);
        return;
      }
      Long userId = ChannelRepository.removeChannel(ctx.channel());
      if (Objects.nonNull(userId) && userId > 0L) {
        log.warn(">>>软件断开链接. 用户=[{}], 客户端=[{}].", userId, DEVICE_HEAD + ctx.channel().id().asShortText());
        execute.handlerContext.getInstance(BasisOptCmd.CMD_CHAT).exception(ctx.channel(), userId);
      }
    }  catch (Exception e) {
      log.error(">>>执行注销管道任务异常!!! ");
      log.error(e.getMessage());
      log.error(ExceptionUtil.getStackTrace(e));
    }  finally {
      if (uuid.equals(execute.redisTemplate.opsForValue().get(lockKey))) {
        log.warn(">>>执行注销管道任务释放锁");
        execute.redisTemplate.delete(lockKey);
      }
    }
    closeChannel(ctx);
  }


  /**
   * 异常连接.
   *
   * @param ctx        管道信息
   * @param cause      异常信息
   * @throws Exception 连接异常
   */
  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    super.exceptionCaught(ctx, cause);
    String uuid = IdUtil.simpleUUID();
    String lockKey = "KEY_LOCK:SOCKET:" + DEVICE_HEAD + ctx.channel().id().asShortText();
    try {
      // 使用Redis加锁
      Boolean ifAbsent = execute.redisTemplate.opsForValue().setIfAbsent(lockKey, uuid, 90, TimeUnit.SECONDS);
      if (!ifAbsent) {
        log.warn(">>>执行注销管道加锁状态, lock_key=[{}]", lockKey);
        return;
      }
      log.warn(">>>Exception_msg={}", cause.getMessage());
      Long userId = ChannelRepository.removeChannel(ctx.channel());
      if (Objects.nonNull(userId) && userId > 0) {
        log.warn(">>>异常关闭链接. 用户=[{}], 客户端=[{}].", userId, DEVICE_HEAD + ctx.channel().id().asShortText());
        execute.handlerContext.getInstance(BasisOptCmd.CMD_CHAT).exception(ctx.channel(), userId);
      }
    }  catch (Exception e) {
      log.error(">>>执行注销管道任务异常!!! ");
      log.error(e.getMessage());
      log.error(ExceptionUtil.getStackTrace(e));
    }  finally {
      if (uuid.equals(execute.redisTemplate.opsForValue().get(lockKey))) {
        log.warn(">>>执行注销管道任务释放锁");
        execute.redisTemplate.delete(lockKey);
      }
    }
    closeChannel(ctx);
  }

  /**
   * 事件触发.
   *
   * @param ctx        管道信息
   * @param evt        状态事件
   * @throws Exception 事件异常
   */
  @Override
  public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
    super.userEventTriggered(ctx, evt);
    if (evt instanceof IdleStateEvent) {
      IdleState e = ((IdleStateEvent) evt).state();
      switch (e) {
        case READER_IDLE:
          long lastTime = ctx.channel().attr(HEART_BEAT).get() != null
              ? ctx.channel().attr(HEART_BEAT).get() : 0L;
          if (lastTime == 0L) {
            log.warn(">>>[心跳检测超时提醒] 长时间未收到客户端=[{}]心跳指令, 关闭当前无效通道.",
                DEVICE_HEAD + ctx.channel().id().asShortText());
            closeChannel(ctx);
            return;
          }
          long millis = (Instant.now().toEpochMilli() - lastTime) / 1000;
          log.warn(">>>[心跳检测超时提醒] 客户端=[{}]. timeout=[{}].",
              DEVICE_HEAD + ctx.channel().id().asShortText(), millis);
          if (millis >= CHECK_HEART_BEAT_TIME) {
            log.warn(">>>[心跳检测超时提醒] 长时间未收到客户端=[{}]心跳指令, 关闭当前无效通道.",
                DEVICE_HEAD + ctx.channel().id().asShortText());
            autoInactive(ctx);
          }
          break;
        case WRITER_IDLE:
          Long userId = ChannelRepository.getUserId(ctx.channel());
          if (ctx.channel().isActive()) {
            if (userId > 0) {
              log.warn(">>>检测到用户({}) 客户端[{}] 疑似与服务器断开链接, 发送心跳指令, 尝试恢复通信...",
                  userId, DEVICE_HEAD + ctx.channel().id().asShortText());
              ChannelFuture channelFuture = ctx.writeAndFlush(
                  new Packet(BasisOptCmd.CMD_HEART, (short) 3, null));
              if (!channelFuture.isSuccess()) {
                log.warn(">>>客户端[{}] 已与服务器断开链接, 关闭当前无效通道.",
                    DEVICE_HEAD + ctx.channel().id().asShortText());
                autoInactive(ctx);
              }
              return;
            }
          } else {
            if (userId > 0) {
              autoInactive(ctx);
            } else {
              closeChannel(ctx);
            }
          }
          break;
        case ALL_IDLE:
          log.warn(">>>触发[ALL_IDLE] [{}]", ctx.channel().id().asShortText());
          break;
        default:
          break;
      }
    }
  }


  /**
   * 关闭管道.
   *
   * @param ctx        管道信息
   * @throws Exception 注销异常
   */
  private void autoInactive(ChannelHandlerContext ctx) {
    Long userId = ChannelRepository.removeChannel(ctx.channel());
    if (Objects.nonNull(userId) && userId > 0) {
      log.warn(">>>系统断开链接. 用户=[{}], 客户端=[{}].", userId, DEVICE_HEAD + ctx.channel().id().asShortText());
      execute.handlerContext.getInstance(BasisOptCmd.CMD_CHAT).exception(ctx.channel(), userId);
    }
    closeChannel(ctx);
  }

  /**
   * 关闭通道.
   *
   * @param ctx 管道信息
   */
  private void closeChannel(ChannelHandlerContext ctx) {
    ctx.flush();
    ctx.close();
    ctx.disconnect();
  }

}

