package com.cp.im.domain.repository;

import com.cp.im.application.service.RabbitMQService;
import com.cp.im.domain.mq.Packet;
import com.cp.im.domain.mq.Message;
import com.cp.im.infrastructure.amqp.FanoutConfig;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.GlobalEventExecutor;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * SocketChannel存储.
 *
 * @author wangcaiwen
 * @version 1.0
 * @since 2020/4/3 18:23
 */

@Slf4j
@Component
public class ChannelRepository {

  private static final Map<Long, Channel> CHANNEL = new ConcurrentHashMap<>();

  private static final Map<Long, ChannelGroup> CHAT_GROUP = new ConcurrentHashMap<>();

  private static final AttributeKey<Long> USER_ID = AttributeKey.newInstance("USER_ID");

  private static RabbitMQService rabbitService;

  public ChannelRepository() {
  }

  @Autowired
  public ChannelRepository(RabbitMQService rabbitService) {
    ChannelRepository.rabbitService = rabbitService;
  }


  /**
   * 获取管道
   */
  public static Map<Long, Channel> getCHANNEL() {
    return CHANNEL;
  }

  /**
   * 管道消息.
   *
   * @param userId 用户ID
   *
   */
  public static Channel getChannel(Long userId) {
    return CHANNEL.get(userId);
  }

  /**
   * 添加通道.
   *
   * @param userId 用户ID
   * @param channel 管道信息
   *
   */
  public static void addChannel(Long userId, Channel channel) {
    channel.attr(USER_ID).set(userId);
    CHANNEL.put(userId, channel);
  }

  /**
   * 移除通道并返回用户ID.
   *
   * @param channel 管道信息
   * @return 用户ID
   *
   */
  public static Long removeChannel(Channel channel) {
    Long userId = channel.attr(USER_ID).get();
    if (userId != null && userId > 0) {
      CHANNEL.remove(userId);
    }
    channel.attr(USER_ID).set(null);
    return userId;
  }

  /**
   * 解绑管道.
   *
   * @param channel 管道信息
   *
   */
  public static void untieChannel(Channel channel) {
    long userId = channel.attr(USER_ID).get();
    if (userId > 0) {
      CHANNEL.remove(userId);
    }
    log.info(">>>解绑管道, 并关闭管道...");
    channel.attr(USER_ID).set(0L);
    channel.flush();
    channel.close();
    channel.disconnect();
  }

  /**
   * 获取与channel关联的用户ID.
   *
   * @param channel 管道信息
   * @return 用户ID
   *
   */
  public static Long getUserId(Channel channel) {
    return (channel.attr(USER_ID).get() != null) ? channel.attr(USER_ID).get() : 0L;
  }


  /**
   * 获取群组管道
   * @return
   */
  public static Map<Long, ChannelGroup> getChatGroup() {
    return CHAT_GROUP;
  }

  /**
   * 删除聊天群组.
   *
   * @param groupId 群组ID
   *
   */
  public static void delChatGroup(Long groupId) {
    CHAT_GROUP.remove(groupId);
  }

  /**
   * 聊天群组.
   *
   * @param groupId 群组ID
   * @return 群组通道
   *
   */
  public static ChannelGroup getChatGroup(Long groupId) {
    return CHAT_GROUP.get(groupId);
  }

  /**
   * 移除管道.
   *
   * @param groupId 群组ID
   * @param channel 管道信息
   *
   */
  public static void removeChatChannel(Long groupId, Channel channel) {
    ChannelGroup group = getChatGroup(groupId);
    if (Objects.nonNull(group)) {
      group.remove(channel);
    }
  }

  /**
   * 发送消息.
   *
   * @param packet 数据包
   * @param groupId 群组ID
   *
   */
  public static void sendPacketToChatGroup(Packet packet, Long groupId) {
    Message mqMessage = new Message();
    mqMessage.setTags(FanoutConfig.GROUP_CHANNEL_TAG);
    mqMessage.setPacket(packet);
    mqMessage.setHandlerType("0");
    mqMessage.setGroupId(groupId);
    rabbitService.send(mqMessage);
  }

  /**
   * 发送消息.
   *
   * @param packet 数据包
   * @param groupId 群组ID
   * @param channel 管道信息
   *
   */
  public static void sendPacketToChatGroup(Packet packet, Long groupId, Channel channel) {
    Long userId = getUserId(channel);
    packet.setUserId(userId);
    Message mqMessage = new Message();
    mqMessage.setTags(FanoutConfig.GROUP_CHANNEL_TAG);
    mqMessage.setHandlerType("1");
    mqMessage.setGroupId(groupId);
    mqMessage.setPacket(packet);
    rabbitService.send(mqMessage);
  }

  /**
   * 刷新群组.
   *
   * @param groupId 群组ID
   * @param channel 管道信息
   *
   */
  public static void refreshChatGroup(Long groupId, Channel channel) {
    if (!CHAT_GROUP.containsKey(groupId)) {
      // 聊天组
      ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
      group.add(channel);
      CHAT_GROUP.putIfAbsent(groupId, group);
    } else {
      // 聊天组
      ChannelGroup group = getChatGroup(groupId);
      group.add(channel);
    }
  }

  /**
   * 发送数据包到指定用户.
   *
   * @param packet 数据包
   * @param userId 用户ID
   *
   */
  public static void sendPacketToUserId(Packet packet, Long userId) {
    packet.setUserId(userId);
    Message mqMessage = new Message();
    mqMessage.setTags(FanoutConfig.CHANNEL_TAG);
    mqMessage.setPacket(packet);
    rabbitService.send(mqMessage);
//    MqAuto.onMessage(mqMessage);
  }

  public static void testRabbitMq(String message) {
    Message mqMessage = new Message();
    mqMessage.setTags(FanoutConfig.TEST_TAG);
    mqMessage.setHandlerType(message);
    rabbitService.send(mqMessage);
  }

}
