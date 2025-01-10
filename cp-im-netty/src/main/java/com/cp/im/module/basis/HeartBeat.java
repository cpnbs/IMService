package com.cp.im.module.basis;

import com.cp.im.application.service.RabbitMQService;
import com.cp.im.domain.mq.Packet;
import com.cp.im.domain.mq.Message;
import com.cp.im.domain.repository.ChannelRepository;
import com.cp.im.domain.repository.ChatRepository;
import com.cp.im.infrastructure.OptionalHandler;
import com.cp.im.infrastructure.amqp.FanoutConfig;
import com.cp.im.infrastructure.annotation.ModuleTag;
import com.cp.im.infrastructure.cmd.BasisOptCmd;
import com.cp.im.infrastructure.constants.SocketKeyConstants;
import com.cp.im.infrastructure.utils.RedisUtils;
import com.cp.im.proto.c10000msg.C10000;
import com.cp.im.utils.ExceptionUtil;
import com.cp.im.utils.StringUtils;
import com.google.protobuf.InvalidProtocolBufferException;
import io.netty.channel.Channel;

import java.util.List;
import java.util.Objects;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 客户端基础处理.
 */
@Slf4j
@Component
@ModuleTag(tag = BasisOptCmd.CMD_HEART)
public class HeartBeat extends OptionalHandler {

  /** 心跳. */
  private static final short HEART = 0;

  /** 登录. */
  private static final short LOGIN = 1;

  /** 建立关系. */
  private static final short RELATION = 2;

  @Autowired
  private RedisUtils redisUtils;

  @Autowired
  private ChatRepository chatRepository;

  @Autowired
  private RabbitMQService rabbitService;

  /**
   * 操作处理.
   *
   * @param channel 通信管道
   * @param packet  数据包
   * @author wangcaiwen
   * @since 2020/5/15 21:09
   */
  @Override
  public void handle(Channel channel, Packet packet) {
    try {
      switch (packet.child) {
        case HEART:
          channel.writeAndFlush(new Packet(BasisOptCmd.CMD_HEART, HEART, null));
          break;
        case LOGIN:
          if (packet.userId == 0L) {
            log.warn(">>>登录失败. 登录数据错误={}", packet);
            channel.writeAndFlush(new Packet(BasisOptCmd.CMD_HEART, LOGIN,
                    C10000.C100001s2c.newBuilder().setErrCode(1).setErrMsg("login data error").build().toByteArray()));
            return;
          }
          if (!validateLogon(channel, packet)) {
            return;
          }

          Channel oldChannel = ChannelRepository.getChannel(packet.userId);
          if (Objects.nonNull(oldChannel)) {
            ChannelRepository.untieChannel(oldChannel);
          }
          ChannelRepository.addChannel(packet.userId, channel);
          channel.writeAndFlush(new Packet(BasisOptCmd.CMD_HEART, LOGIN,
                  C10000.C100001s2c.newBuilder().setErrCode(0).build().toByteArray()));

          break;
        default:
          log.warn(">>>错误的指令. CMD=[10000], CHILD=[{}]", packet.child);
          break;
      }
    } catch (Exception e) {
      log.error(e.getMessage());
      log.error(ExceptionUtil.getStackTrace(e));
    }
  }

  /**
   * 登录验证.
   *
   * @param channel 通信管道
   * @param packet  数据包
   * @return 验证结果
   */
  private boolean validateLogon(Channel channel, Packet packet) {
    try {
      C10000.C100001c2s request = C10000.C100001c2s.parseFrom(packet.bytes);
      Integer identity = request.getIdentity();
      String deviceId = request.getDeviceId();
      String token = request.getToken();
      Long userId = packet.getUserId();
      log.warn(">>>用户登录数据. user_id=[{}], identity=[{}], device_id=[{}], token=[{}].", userId, identity, deviceId, token);

      String myToken = chatRepository.getToken(userId);
      if (Objects.equals(token, myToken)) {
        log.warn(">>>登录成功. user_id=[{}]", userId);

        if (identity == 0){ //普通用户，初始化聊天信息
          List<Long> targetList = chatRepository.getTargetList(userId);
          targetList.remove(userId);
          targetList.forEach(targetId -> {
            chatRepository.buildRelation(userId, targetId);
          });
        }

        singleSignOn(userId, deviceId);
        return true;
      }
      log.warn(">>>登录失败. user_id=[{}], token [ NotExist | Invalid ] !!!", userId);
      channel.writeAndFlush(new Packet(BasisOptCmd.CMD_HEART, LOGIN,
              C10000.C100001s2c.newBuilder().setErrCode(1).setErrMsg("login token error").build().toByteArray()));
    } catch (InvalidProtocolBufferException e) {
      log.error(e.getMessage());
      log.error(ExceptionUtil.getStackTrace(e));
    }
    return false;
  }

  /**
   * 异常登录通知.
   *
   * @param userId   用户ID
   * @param deviceId 设备ID
   */
  private void singleSignOn(Long userId, String deviceId) {
    log.warn(">>>客户端设备号=[{}]", deviceId);
    String key = SocketKeyConstants.KEY_LOGIN_DEVICE + userId;
    String value = (String) redisUtils.get(key);
    log.warn(">>>Redis存储的客户端设备号=[{}]", value);
    if (StringUtils.isEmpty(value)) {
      redisUtils.set(key, deviceId);
    } else {
      if (!Objects.equals(value, deviceId)) {
        log.warn(">>>用户多处登录. user_id=[{}]", userId);
        if (Objects.nonNull(userId) && userId > 0) {
          Packet packet = new Packet(BasisOptCmd.CMD_HEART, LOGIN,
              C10000.C100001s2c.newBuilder().setErrCode(1).setErrMsg("Multiple logins").build().toByteArray());
          packet.setUserId(userId);
          Message mqMessage = new Message();
          mqMessage.setTags(FanoutConfig.CHANNEL_TAG);
          mqMessage.setPacket(packet);
          mqMessage.setHandlerType("1");
          rabbitService.send(mqMessage);
//          MqAuto.onMessage(mqMessage);
          try {
            Thread.sleep(500);
          } catch (InterruptedException e) {
            log.error(e.getMessage());
            log.error(ExceptionUtil.getStackTrace(e));
          }
        }
        // 更新设备号
        this.redisUtils.set(key, deviceId);
      } else {
        log.warn(">>>用户重新登录. user_id=[{}]", userId);
      }
    }
  }

}
