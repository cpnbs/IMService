package com.cp.im.application.task;

import com.cp.im.constants.RedisKey;
import com.cp.im.domain.repository.ChannelRepository;
import com.cp.im.infrastructure.constants.ChatKeyConstants;
import com.cp.im.infrastructure.utils.RedisUtils;
import io.netty.channel.Channel;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 通知任务.
 *
 * @author wangcaiwen
 * @version v1.0.0
 * @since 2021/3/4 15:31
 */

@Component
public class NoticeTask {

  private static final Logger logger = LoggerFactory.getLogger(NoticeTask.class);

  @Autowired
  private RedisUtils redisUtils;

  /**
   * 缓存初始化.
   *
   * @author wangcaiwen
   * @since 2021/4/30 15:09
   */
  @PostConstruct
  private void init() {
    logger.info("[Initial Cache] initial login Cache success");
    Set<String> list1 = this.redisUtils.keys(ChatKeyConstants.KEY_CHAT_GROUP_LOGIN + "*");
    this.redisUtils.delKeys(list1);
    Set<String> list2 = this.redisUtils.keys(ChatKeyConstants.KEY_CHAT_GROUP_ONLINE + "*");
    this.redisUtils.delKeys(list2);
  }

  /**
   * 每日凌晨青少年模式提醒.
   *
   * @author wangcaiwen
   * @since 2021/3/4 15:33
   */
//  @Scheduled(cron = "0 0 0 * * ?")
  public void youthModeReminder(){
    logger.info("[INFO] 青少年模式提醒");
//    Message mqMessage = new Message();
//    mqMessage.setTags(Tags.ONLINE_CHANNEL_TAG);
//    mqMessage.setPacket(new Packet(BasisOptCmd.CMD_NOTICE, (short) 4, null));
//    this.messageQueueService.asyncSend(mqMessage);
  }

  /**
   * Redis管道更新.
   *
   * @author wangxuewen
   * @since 2021/6/16 13:07
   */
  @Scheduled(cron = "${scheduledCron.channel}")
  public void redisChannelUpdate() throws UnknownHostException {
    Map<Long, Channel> channelMap = ChannelRepository.getCHANNEL();
    Set<Long> userIdSet = channelMap.keySet();
    for (Long userId : userIdSet) {
      Channel channel = ChannelRepository.getChannel(userId);
      if (channel != null && channel.isActive()){
        redisUtils.set(RedisKey.KEY_NETTY_USER_LOGIN + userId, InetAddress.getLocalHost().getHostName(),4);
      }
    }
  }

}
