package com.cp.im.module.notice;

import com.cp.im.application.service.RabbitMQService;
import com.cp.im.domain.mq.Packet;
import com.cp.im.domain.mq.Message;
import com.cp.im.domain.repository.ChannelRepository;
import com.cp.im.infrastructure.OptionalHandler;
import com.cp.im.infrastructure.annotation.ModuleTag;
import com.cp.im.infrastructure.cmd.BasisOptCmd;
import com.cp.im.infrastructure.cmd.PopupNoticeCmd;
import com.cp.im.infrastructure.cmd.PopupNoticeReqCmd;
import com.cp.im.infrastructure.mapper.ChatSingleMapper;
import com.cp.im.manager.ZoneEnum;
import com.cp.im.proto.c10001msg.C10001;
import com.cp.im.result.Result;
import com.cp.im.utils.StringUtils;
import io.netty.channel.Channel;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Map;
import java.util.Objects;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 通知处理.
 */
@Slf4j
@Component
@ModuleTag(tag = BasisOptCmd.CMD_NOTICE)
public class PopupNotice extends OptionalHandler {

  @Autowired
  private ChatSingleMapper chatSingleMapper;

  /**
   * 操作处理.
   *
   * @param channel 通信管道
   * @param packet 数据包
   */
  @Override
  public void handle(Channel channel, Packet packet) {
    // EMPTY_HANDLER
  }

  /**
   * 处理扩展.
   *
   * @param index  处理索引
   * @param params 处理参数
   */
  @Override
  public void handleExtra(short index, Map<String, Object> params) {
    switch (index) {
      case PopupNoticeReqCmd.SINGLE_CHAT_NOTICE:
        sendSingleChatNotice(params);
        break;
      default:
        log.error("[ERROR] Unknown. Cmd=[10001], index=[{}].", index);
        break;
    }
  }

  /**
   * 发送单聊通知.
   * @param params 消息内容
   */
  private void sendSingleChatNotice(Map<String, Object> params) {
    Integer sort = MapUtils.getInteger(params, "sort");
    if (sort == 1) {
      C10001.C100012s2c.Builder builder = C10001.C100012s2c.newBuilder();
      Long userId = MapUtils.getLong(params, "userId");
      Long targetId = MapUtils.getLong(params, "targetId");
      Integer messageType =  MapUtils.getInteger(params, "type");

      Map<String, Object> newResult = this.chatSingleMapper.getUserBase2(targetId, userId);
      if (Objects.nonNull(newResult)) {
        builder.addThumbIconURL(StringUtils.nvl(newResult.get("iconUrl")));
        builder.setAttachId(userId);
        builder.setSort(1);
        builder.setMessage(StringUtils.nvl(params.get("message")));
        // 0 文本 1 Emoji 2 图片 3 语音 4 视频
        if (messageType > 0) {
          String message;
          switch (messageType) {
            case 1:
              message = "[表情]";
              break;
            case 2:
              message = "[图片]";
              break;
            case 3:
              message = "[语音]";
              break;
            case 4:
              message = "[视频]";
              break;
            default:
              message = "[~~]";
              break;
          }
          builder.setMessage(message);
        }
        builder.setMessageType(messageType);
        String alias = MapUtils.getString(newResult, "alias");
        String nickName = StringUtils.nvl(newResult.get("nickName"));
        builder.setAlias(alias.length() > 0 ? alias : nickName);
        builder.setMobile(MapUtils.getString(newResult, "mobile"));
        builder.setFlagTop(MapUtils.getInteger(newResult, "flagTop"));
        builder.setFlagStar(MapUtils.getInteger(newResult, "flagStar"));
        builder.setNotDisturb(MapUtils.getInteger(newResult, "notDisturb"));
        builder.setUnreadNum(MapUtils.getInteger(newResult, "unreadNum"));
        builder.setLastTimestamp(LocalDateTime.now().toEpochSecond(ZoneOffset.of(ZoneEnum.E8.getZone())));

        ChannelRepository.sendPacketToUserId(
                new Packet(BasisOptCmd.CMD_NOTICE, PopupNoticeCmd.CHAT_NOTICE,
                        builder.build().toByteArray()), targetId);
        log.warn(">>>发送单聊通知 [{}] and [{}]", userId, targetId);
      }
    }
  }

  /**
   * 请求处理.
   *
   * @param index  请求索引
   * @param params 请求参数
   * @return 请求结果
   */
  @Override
  public Result<?> request(short index, Map<String, Object> params) {
    switch (index) {
      case PopupNoticeReqCmd.SYS_SOFTWARE_NOTICE:
        return softwareNotice(params);
      default:
        log.error("[ERROR] Unknown. Cmd=[10001], RequestIndex=[{}].", index);
        return Result.error();
    }
  }


  /**
   * 软件通知.
   *
   * @param params 通知信息
   * @return 调用结果
   * @author wangcaiwen
   * @since 2021/4/3 16:40
   */
  private Result<?> softwareNotice(Map<String, Object> params) {
    C10001.C100011s2c.Builder builder = C10001.C100011s2c.newBuilder();
    // 通知类型 [0全局推送 1个人推送]
    Integer noticeType = (Integer) params.get("noticeType");
    builder.setNoticeType(noticeType);
    Message mqMessage = new Message();
    switch (noticeType) {
      // 当前在线的所有用户
      case 0:
//        builder.setTitle(StringUtils.nvl(params.get("contentTitle")));
//        builder.setContent(StringUtils.nvl(params.get("contentIntro")));
//        mqMessage.setTags(Tags.ONLINE_CHANNEL_TAG);
//        mqMessage.setPacket(new Packet(BasisOptCmd.CMD_NOTICE,
//            PopupNoticeCmd.SOFTWARE_NOTICE, builder.build().toByteArray()));
        break;
      // 个人用户推送
      default:
        Long userId = ((Number) params.get("userId")).longValue();
        builder.setTitle(StringUtils.nvl(params.get("contentTitle")));
        builder.setContent(StringUtils.nvl(params.get("content")));
        ChannelRepository.sendPacketToUserId(new Packet(BasisOptCmd.CMD_NOTICE,
            PopupNoticeCmd.SOFTWARE_NOTICE, builder.build().toByteArray()), userId);
        break;
    }
    return Result.success();
  }

}
