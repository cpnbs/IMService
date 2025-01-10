package com.cp.im.module.chat.opt;

import com.cp.im.application.service.RabbitMQService;
import com.cp.im.constants.RedisKey;
import com.cp.im.domain.dto.InsertSingleMsgDTO;
import com.cp.im.domain.entity.ChatSingleFile;
import com.cp.im.domain.mq.Packet;
import com.cp.im.domain.mq.Message;
import com.cp.im.domain.repository.ChatRepository;
import com.cp.im.infrastructure.OptionalHandler;
import com.cp.im.infrastructure.amqp.FanoutConfig;
import com.cp.im.infrastructure.annotation.ModuleTag;
import com.cp.im.infrastructure.cmd.BasisOptCmd;
import com.cp.im.infrastructure.cmd.ChitchatCmd;
import com.cp.im.infrastructure.cmd.PopupNoticeReqCmd;
import com.cp.im.infrastructure.constants.ChatKeyConstants;
import com.cp.im.infrastructure.factory.HandlerContext;
import com.cp.im.infrastructure.utils.RedisUtils;
import com.cp.im.manager.ZoneEnum;
import com.cp.im.module.chat.ChatTypeTag;
import com.cp.im.proto.c10002msg.C10002;
import com.cp.im.result.Result;
import com.cp.im.utils.ExceptionUtil;
import com.cp.im.utils.StringUtils;
import io.netty.channel.Channel;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 单聊处理.
 */

@Slf4j
@Component
@ModuleTag(tag = ChatTypeTag.SINGLE_CHAT)
public class OptSingleChat extends OptionalHandler {

  private static final int CONTENT_MAX = 1000;

  @Autowired
  private RedisUtils redisUtils;

  @Autowired
  private ChatRepository chatRepository;

  @Autowired
  private RabbitMQService rabbitService;

  @Resource
  private HandlerContext handlerContext;

  /**
   * 操作处理.
   *
   * @param channel 通信管道
   * @param packet 数据包
   */
  @Override
  public void handle(Channel channel, Packet packet) {
    switch (packet.child) {
      // 进入聊天
      case ChitchatCmd.ENTER_CHAT_ROOM:
        enterChatRoom(channel, packet);
        break;
      // 聊天信息
      case ChitchatCmd.SEND_MESSAGE:
        sendChatMessage(channel, packet);
        break;
      // 离开聊天
      case ChitchatCmd.LEAVE_CHAT_ROOM:
        leaveChatRoom(channel, packet);
        break;
      // 聆听语音
      case ChitchatCmd.LISTEN_VOICE:
        listenVoice(channel, packet);
        break;
      default:
        log.error("[ERROR] Unknown. Packet: Cmd=[10002], Child=[{}].", packet.child);
        break;
    }
  }

  /**
   * 进入聊天室.
   *
   * @param channel 通信管道
   * @param packet 数据包
   */
  private void enterChatRoom(Channel channel, Packet packet) {
    try {
      C10002.C100020s2c.Builder builder = C10002.C100020s2c.newBuilder();

      C10002.C100020c2s request = C10002.C100020c2s.parseFrom(packet.bytes);
      if (packet.userId == 0L || request.getTargetId() == 0L) {
        channel.writeAndFlush(new Packet(BasisOptCmd.CMD_CHAT, ChitchatCmd.ENTER_CHAT_ROOM,
                builder.setErrCode(1).setErrMsg("userId or targetId data null").build().toByteArray()));
        log.warn(">>>进入聊天室失败，userId or targetId data null，[{}] and [{}]", packet.userId, request.getTargetId());
        return;
      }

      String redisKey = StringUtils.nvl(packet.userId);
      if (this.redisUtils.hHasKey(ChatKeyConstants.KEY_CHAT_SINGLE_LOGIN, redisKey)) {
        this.chatRepository.updateUserChatStatusAll(packet.userId);
        this.redisUtils.hdel(ChatKeyConstants.KEY_CHAT_SINGLE_LOGIN, redisKey);
      }

      Map<String, Object> targetUserBase = this.chatRepository.getUserBase(request.getTargetId(), packet.userId);
      if (MapUtils.isEmpty(targetUserBase)){
        channel.writeAndFlush(new Packet(BasisOptCmd.CMD_CHAT, ChitchatCmd.ENTER_CHAT_ROOM,
                builder.setErrCode(1).setErrMsg("targetUserBase is null").build().toByteArray()));
        log.warn(">>>进入聊天室失败，关联关系为空，[{}] and [{}]", packet.userId, request.getTargetId());
        return;
      }

      C10002.ESUserInfo.Builder userInfo = C10002.ESUserInfo.newBuilder();
      userInfo.setUserId(MapUtils.getLong(targetUserBase, "userId"));
      userInfo.setMobile(MapUtils.getString(targetUserBase, "mobile"));
      String alias = MapUtils.getString(targetUserBase, "alias");
      userInfo.setAlias(alias.length() > 0 ? alias : MapUtils.getString(targetUserBase, "nickName"));
      userInfo.setIconURL(MapUtils.getString(targetUserBase, "iconUrl"));
      builder.addUserInfo(userInfo);

      channel.writeAndFlush(new Packet(BasisOptCmd.CMD_CHAT, ChitchatCmd.ENTER_CHAT_ROOM,
              builder.setErrCode(0).build().toByteArray()));
      log.warn(">>>进入聊天室成功，[{}] and [{}]", packet.userId, request.getTargetId());

      this.chatRepository.updateSingleChatStatus(packet.userId, request.getTargetId(), 1);
      this.redisUtils.hset(ChatKeyConstants.KEY_CHAT_SINGLE_LOGIN, redisKey, request.getTargetId());
    } catch (Exception e) {
      log.error(e.getMessage());
      log.error(ExceptionUtil.getStackTrace(e));
    }
  }

  /**
   * 发送消息.
   *
   * @param channel 通信管道
   * @param packet 数据包
   */
  private void sendChatMessage(Channel channel, Packet packet) {
    try {
      C10002.C100021c2s request = C10002.C100021c2s.parseFrom(packet.bytes);
      if (packet.userId == 0L || request.getTargetId() == 0L) {
        sendFailed(channel, request, 7, 0);
        return;
      }
      String redisKey = StringUtils.nvl(packet.userId);
      if (!this.redisUtils.hHasKey(ChatKeyConstants.KEY_CHAT_SINGLE_LOGIN, redisKey)) {
        this.redisUtils.hset(ChatKeyConstants.KEY_CHAT_SINGLE_LOGIN, redisKey, request.getTargetId());
      }
      boolean target = redisUtils.hasKey(RedisKey.KEY_NETTY_USER_LOGIN + request.getTargetId());
      if (target) {
        onlineChat(channel, packet);
        return;
      }
      offlineChat(channel, packet);
    } catch (Exception e) {
      log.error(e.getMessage());
      log.error(ExceptionUtil.getStackTrace(e));
    }
  }

  /**
   * 在线单聊.
   *
   * @param channel 管道信息
   * @param packet 数据包
   */
  private void onlineChat(Channel channel, Packet packet) {
    try {
      C10002.C100021c2s request = C10002.C100021c2s.parseFrom(packet.bytes);
      switch (request.getMessageType()) {
        // Emoji
        case 1:
          sendEmojiToSingleChat(channel, packet);
          break;
        // 图片&视频
        case 2:
        case 4:
          sendImageOrVideoToSingleChat(channel, packet);
          break;
        // 语音
        case 3:
          sendVoiceToSingleChat(channel, packet);
          break;
        // 文本
        default:
          sendTextToSingleChat(channel, packet);
          break;
      }
    } catch (Exception e) {
      log.error(e.getMessage());
      log.error(ExceptionUtil.getStackTrace(e));
    }
  }

  /**
   * 在线单聊-发送表情.
   *
   * @param channel 管道信息
   * @param packet 数据包
   */
  private void sendEmojiToSingleChat(Channel channel, Packet packet) {
    try {
      C10002.C100021c2s request = C10002.C100021c2s.parseFrom(packet.bytes);

      InsertSingleMsgDTO insertInfo = new InsertSingleMsgDTO();
      insertInfo.setUserId(packet.userId);
      insertInfo.setTargetId(request.getTargetId());
      insertInfo.setMessageId(request.getMessageId());
      insertInfo.setType(request.getMessageType());
      insertInfo.setSendTime(LocalDateTime.now());

      insertInfo.setEmoji(request.getEmojiInfo().getEmName());
      insertInfo.setEmojiUrl(request.getEmojiInfo().getAnimURL());

      Object redisValue = this.redisUtils.hget(ChatKeyConstants.KEY_CHAT_SINGLE_LOGIN, StringUtils.nvl(request.getTargetId()));
      if (Objects.nonNull(redisValue) && Long.parseLong(String.valueOf(redisValue)) == packet.userId) {
        saveMessage(channel, request, insertInfo, 1, 1);
        return;
      }
      saveMessage(channel, request, insertInfo, 0, 0);
    } catch (Exception e) {
      log.error(e.getMessage());
      log.error(ExceptionUtil.getStackTrace(e));
    }
  }

  /**
   * 在线单聊-发送图片视频.
   *
   * @param channel 管道信息
   * @param packet 数据包
   */
  private void sendImageOrVideoToSingleChat(Channel channel, Packet packet) {
    try {
      C10002.C100021c2s request = C10002.C100021c2s.parseFrom(packet.bytes);

      Map<String, Object> fileResult = this.chatRepository.fileUpload(request, 0, packet.userId);
      if (Objects.nonNull(fileResult)) {
        InsertSingleMsgDTO insertInfo = new InsertSingleMsgDTO();
        insertInfo.setUserId(packet.userId);
        insertInfo.setTargetId(request.getTargetId());
        insertInfo.setMessageId(request.getMessageId());
        insertInfo.setType(request.getMessageType());
        insertInfo.setSendTime(LocalDateTime.now());

        ChatSingleFile messageFile = new ChatSingleFile();
        if (request.getMessageType() == 2){
          messageFile.setFileType(0);
          messageFile.setFileWidth(request.getImageInfo().getWidth());
          messageFile.setFileHeight(request.getImageInfo().getHeight());
          messageFile.setFileUrl(fileResult.get("fileUrl").toString());
        } else {
          messageFile.setFileType(2);
          messageFile.setFileDuration(request.getVideoInfo().getDuration());
          messageFile.setFileWidth(request.getVideoInfo().getWidth());
          messageFile.setFileHeight(request.getVideoInfo().getHeight());
          messageFile.setFileUrl(fileResult.get("fileUrl").toString());
          messageFile.setFileCoverUrl(fileResult.get("coverUrl").toString());
        }
        insertInfo.setMessageFile(messageFile);

        Object redisValue = this.redisUtils.hget(ChatKeyConstants.KEY_CHAT_SINGLE_LOGIN, StringUtils.nvl(request.getTargetId()));
        if (Objects.nonNull(redisValue) && Long.parseLong(String.valueOf(redisValue)) == packet.userId) {
          saveMessage(channel, request, insertInfo, 1, 1);
          return;
        }
        saveMessage(channel, request, insertInfo, 0, 0);
        return;
      }
      sendFailed(channel, request, 3, request.getMessageType());
    } catch (Exception e) {
      log.error(e.getMessage());
      log.error(ExceptionUtil.getStackTrace(e));
    }
  }

  /**
   * 在线单聊-发送语音.
   *
   * @param channel 管道信息
   * @param packet 数据包
   */
  private void sendVoiceToSingleChat(Channel channel, Packet packet) {
    try {
      C10002.C100021c2s request = C10002.C100021c2s.parseFrom(packet.bytes);
      Map<String, Object> fileResult = this.chatRepository.fileUpload(request, 0, packet.userId);
      if (Objects.nonNull(fileResult)) {
        InsertSingleMsgDTO insertInfo = new InsertSingleMsgDTO();
        insertInfo.setUserId(packet.userId);
        insertInfo.setTargetId(request.getTargetId());
        insertInfo.setMessageId(request.getMessageId());
        insertInfo.setType(request.getMessageType());
        insertInfo.setSendTime(LocalDateTime.now());

        ChatSingleFile messageFile = new ChatSingleFile();
        messageFile.setFileType(1);
        messageFile.setFileDuration(request.getVoiceInfo().getDuration());
        messageFile.setFileUrl(fileResult.get("fileUrl").toString());
        insertInfo.setMessageFile(messageFile);

        Object redisValue = this.redisUtils.hget(ChatKeyConstants.KEY_CHAT_SINGLE_LOGIN, StringUtils.nvl(request.getTargetId()));
        if (Objects.nonNull(redisValue) && Long.parseLong(String.valueOf(redisValue)) == packet.userId) {
          saveMessage(channel, request, insertInfo, 1, 0);
          return;
        }
        saveMessage(channel, request, insertInfo, 0, 0);
        return;
      }
      sendFailed(channel, request, 4, 0);
    } catch (Exception e) {
      log.error(e.getMessage());
      log.error(ExceptionUtil.getStackTrace(e));
    }
  }

  /**
   * 在线单聊-发送文本.
   *
   * @param channel 管道信息
   * @param packet 数据包
   */
  private void sendTextToSingleChat(Channel channel, Packet packet) {
    try {
      C10002.C100021c2s request = C10002.C100021c2s.parseFrom(packet.bytes);
      if (request.getMessage().length() <= CONTENT_MAX) {
        InsertSingleMsgDTO insertInfo = new InsertSingleMsgDTO();
        insertInfo.setUserId(packet.userId);
        insertInfo.setTargetId(request.getTargetId());
        insertInfo.setMessageId(request.getMessageId());
        insertInfo.setType(request.getMessageType());
        insertInfo.setSendTime(LocalDateTime.now());
        insertInfo.setMessage(request.getMessage());

        Object redisValue = this.redisUtils.hget(ChatKeyConstants.KEY_CHAT_SINGLE_LOGIN, StringUtils.nvl(request.getTargetId()));
        if (Objects.nonNull(redisValue) && Long.parseLong(String.valueOf(redisValue)) == packet.userId) {
          saveMessage(channel, request, insertInfo, 1, 1);
          return;
        }
        saveMessage(channel, request, insertInfo, 0, 0);
        return;
      }
      sendFailed(channel, request, 100, 0);
    } catch (Exception e) {
      log.error(e.getMessage());
      log.error(ExceptionUtil.getStackTrace(e));
    }
  }

  /**
   * 保存消息.
   *
   * @param channel 管道信息
   * @param request 请求信息
   * @param insertInfo 消息内容
   * @param readStatus 阅读状态
   * @param action 操作状态
   */
  private void saveMessage(Channel channel, C10002.C100021c2s request, InsertSingleMsgDTO insertInfo,
                           Integer readStatus, Integer action) {

    insertInfo.setMessageRead(readStatus);
    if (readStatus == 1) {
      Long recordId = this.chatRepository.saveSingleChatGetRecordId(insertInfo);
      if (recordId > 0) {
        insertInfo.setRecordId(recordId);
        sendSuccess(channel, request, recordId);
        this.chatRepository.updateSingleChatNoticeList(insertInfo, 1, 0);
        //给目标发信息
        forwardMessage(insertInfo, action);
        return;
      }
      sendFailed(channel, request, 2, 0);
      return;
    }
    Long recordId = this.chatRepository.saveSingleChatGetRecordId(insertInfo);
    if (recordId > 0) {
      insertInfo.setRecordId(recordId);
      sendSuccess(channel, request, recordId);
      this.chatRepository.updateSingleChatNoticeList(insertInfo, 0, 0);

      Map<String, Object> message =  new HashMap<>();
      message.put("sort", 1);
      message.put("userId", insertInfo.getUserId());
      message.put("targetId", insertInfo.getTargetId());
      message.put("type", insertInfo.getType());
      message.put("message", insertInfo.getMessage());
      sendSingleChatNotice(message);
      return;
    }
    sendFailed(channel, request, 2, 0);
  }

  /**
   * 离线单聊.
   * @param channel 管道信息
   * @param packet 数据包
   */
  private void offlineChat(Channel channel, Packet packet) {
    try {
      C10002.C100021c2s request = C10002.C100021c2s.parseFrom(packet.bytes);
      switch (request.getMessageType()) {
        case 0:
          // 文本
          offlineTextToSingleChat(channel, packet);
          break;
        case 1:
          // Emoji
          offlineEmojiToSingleChat(channel, packet);
          break;
        default:
          // 图片/语音/视频
          offlineFileToSingleChat(channel, packet);
          break;
      }
    } catch (Exception e) {
      log.error(e.getMessage());
      log.error(ExceptionUtil.getStackTrace(e));
    }
  }

  /**
   * 离线单聊-发送文本.
   * @param channel 管道信息
   * @param packet 数据包
   */
  private void offlineTextToSingleChat(Channel channel, Packet packet) {
    try {
      C10002.C100021c2s request = C10002.C100021c2s.parseFrom(packet.bytes);
      String content = request.getMessage();
      int contentLength = content.length();
      if (contentLength <= CONTENT_MAX) {

        InsertSingleMsgDTO insertInfo = new InsertSingleMsgDTO();
        insertInfo.setUserId(packet.userId);
        insertInfo.setTargetId(request.getTargetId());
        insertInfo.setMessageId(request.getMessageId());
        insertInfo.setType(request.getMessageType());
        insertInfo.setSendTime(LocalDateTime.now());
        insertInfo.setMessage(request.getMessage());

        insertInfo.setMessageRead(0);
        Long recordId = this.chatRepository.saveSingleChatGetRecordId(insertInfo);
        if (recordId > 0) {
          sendSuccess(channel, request, recordId);
          insertInfo.setRecordId(recordId);
          this.chatRepository.updateSingleChatNoticeList(insertInfo, 0, 0);
          return;
        }
        sendFailed(channel, request, 2, 0);
        return;
      }
      sendFailed(channel, request, 100, 0);
    } catch (Exception e) {
      log.error(e.getMessage());
      log.error(ExceptionUtil.getStackTrace(e));
    }
  }

  /**
   * 离线单聊-发送表情.
   * @param channel 管道信息
   * @param packet 数据包
   */
  private void offlineEmojiToSingleChat(Channel channel, Packet packet) {
    try {
      C10002.C100021c2s request = C10002.C100021c2s.parseFrom(packet.bytes);

      InsertSingleMsgDTO insertInfo = new InsertSingleMsgDTO();
      insertInfo.setUserId(packet.userId);
      insertInfo.setTargetId(request.getTargetId());
      insertInfo.setMessageId(request.getMessageId());
      insertInfo.setType(request.getMessageType());
      insertInfo.setSendTime(LocalDateTime.now());
      insertInfo.setMessage(request.getMessage());

      insertInfo.setEmoji(request.getEmojiInfo().getEmName());
      insertInfo.setEmojiUrl(request.getEmojiInfo().getAnimURL());

      insertInfo.setMessageRead(0);
      Long recordId = this.chatRepository.saveSingleChatGetRecordId(insertInfo);
      if (recordId > 0) {
        sendSuccess(channel, request, recordId);
        insertInfo.setRecordId(recordId);
        this.chatRepository.updateSingleChatNoticeList(insertInfo, 0, 0);
        return;
      }
      sendFailed(channel, request, 2, 0);
    } catch (Exception e) {
      log.error(e.getMessage());
      log.error(ExceptionUtil.getStackTrace(e));
    }
  }

  /**
   * 离线单聊-发送文件.
   * @param channel 管道信息
   * @param packet 数据包
   */
  private void offlineFileToSingleChat(Channel channel, Packet packet) {
    try {
      C10002.C100021c2s request = C10002.C100021c2s.parseFrom(packet.bytes);
      Map<String, Object> fileResult = this.chatRepository.fileUpload(request, 0, packet.userId);
      if (Objects.nonNull(fileResult)) {
        InsertSingleMsgDTO insertInfo = new InsertSingleMsgDTO();
        insertInfo.setUserId(packet.userId);
        insertInfo.setTargetId(request.getTargetId());
        insertInfo.setMessageId(request.getMessageId());
        insertInfo.setType(request.getMessageType());
        insertInfo.setSendTime(LocalDateTime.now());
        insertInfo.setMessage(request.getMessage());

        ChatSingleFile messageFile = new ChatSingleFile();
        switch (request.getMessageType()){
          case 2:
            messageFile.setFileType(0);
            messageFile.setFileWidth(request.getImageInfo().getWidth());
            messageFile.setFileHeight(request.getImageInfo().getHeight());
            messageFile.setFileUrl(fileResult.get("fileUrl").toString());
            break;
          case 3:
            messageFile.setFileType(1);
            messageFile.setFileDuration(request.getVoiceInfo().getDuration());
            messageFile.setFileUrl(fileResult.get("fileUrl").toString());
            break;
          case 4:
            messageFile.setFileType(2);
            messageFile.setFileDuration(request.getVideoInfo().getDuration());
            messageFile.setFileWidth(request.getVideoInfo().getWidth());
            messageFile.setFileHeight(request.getVideoInfo().getHeight());
            messageFile.setFileUrl(fileResult.get("fileUrl").toString());
            messageFile.setFileCoverUrl(fileResult.get("coverUrl").toString());
            break;
        }
        insertInfo.setMessageFile(messageFile);

        insertInfo.setMessageRead(0);
        Long recordId = this.chatRepository.saveSingleChatGetRecordId(insertInfo);
        if (recordId > 0) {
          sendSuccess(channel, request, recordId);
          insertInfo.setRecordId(recordId);
          this.chatRepository
              .updateSingleChatNoticeList(insertInfo, 0, 0);
          return;
        }
      }
      if (request.getMessageType() == 3) {
        sendFailed(channel, request, 4, 0);
        return;
      }
      sendFailed(channel, request, 3, request.getMessageType());
    } catch (Exception e) {
      log.error(e.getMessage());
      log.error(ExceptionUtil.getStackTrace(e));
    }
  }

  /**
   * 发送单聊弹窗提醒.
   * @param message 消息内容
   */
  private void sendSingleChatNotice(Map<String, Object> message) {
    if (Objects.nonNull(message) && MapUtils.isNotEmpty(message)) {
      this.handlerContext.getInstance(BasisOptCmd.CMD_NOTICE)
              .handleExtra(PopupNoticeReqCmd.SINGLE_CHAT_NOTICE, message);
    }
  }

  /**
   * 单聊-推送消息.
   * @param insertInfo 消息详情
   */
  private void forwardMessage(InsertSingleMsgDTO insertInfo, Integer action) {
    C10002.C100022s2c.Builder sendBuilder = C10002.C100022s2c.newBuilder();
    switch (insertInfo.getType()) {
      case 1:
        sendBuilder.setEmojiInfo(this.chatRepository.emojiBuilder(insertInfo));
        break;
      case 2:
        sendBuilder.setImageInfo(this.chatRepository.imageBuilder(insertInfo));
        break;
      case 3:
        sendBuilder.setVoiceInfo(this.chatRepository.voiceBuilder(insertInfo));
        break;
      case 4:
        sendBuilder.setVideoInfo(this.chatRepository.videoBuilder(insertInfo));
        break;
      default:
        sendBuilder.setMessage(insertInfo.getMessage());
        break;
    }
    sendBuilder.setSort(1);
    sendBuilder.setRecordId(insertInfo.getRecordId());
    sendBuilder.setUserId(insertInfo.getUserId());
    sendBuilder.setMessageId(insertInfo.getMessageId());
    sendBuilder.setMessageType(insertInfo.getType());
    sendBuilder.setIsAction(action);
//    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//    sendBuilder.setCreateTime(dtf.format(insertInfo.getSendTime()));
    sendBuilder.setCreateTime(String.valueOf(insertInfo.getSendTime().toEpochSecond(ZoneOffset.of(ZoneEnum.E8.getZone()))));
    Packet packet = new Packet(BasisOptCmd.CMD_CHAT, ChitchatCmd.RECEIVE_MESSAGE,
        sendBuilder.build().toByteArray());
    packet.setUserId(insertInfo.getTargetId());
    Message mqMessage = new Message();
    mqMessage.setTags(FanoutConfig.CHANNEL_TAG);
    mqMessage.setPacket(packet);
    rabbitService.send(mqMessage);
//    MqAuto.onMessage(mqMessage);
  }

  /**
   * 发送成功.
   *
   * @param channel 管道信息
   * @param request 请求信息
   * @param recordId 记录ID
   */
  private void sendSuccess(Channel channel, C10002.C100021c2s request, Long recordId) {
    C10002.C100021s2c.Builder builder = C10002.C100021s2c.newBuilder();
    builder.setErrCode(0).setRecordId(recordId).setMessageId(request.getMessageId()).setTargetId(request.getTargetId());
    channel.writeAndFlush(
        new Packet(BasisOptCmd.CMD_CHAT, ChitchatCmd.SEND_MESSAGE,
            builder.build().toByteArray()));
    log.warn(">>>发送成功,{}", recordId);
  }

  /**
   * 发送失败.
   * @param channel 管道信息
   */
  private void sendFailed(Channel channel, C10002.C100021c2s request, Integer index, Integer type) {
    C10002.C100021s2c.Builder builder = C10002.C100021s2c.newBuilder();
    switch (index) {
      case 1:
        builder.setErrMsg("fail in send");
        break;
      case 2:
        builder.setErrMsg("Sending failed, request busy");
        break;
      case 3:
        builder.setErrMsg(type == 2 ? "Picture sending failed" : "Video sending failed");
        break;
      case 4:
        builder.setErrMsg("Voice transmission failed");
        break;
      case 5:
        builder.setErrMsg("The content has violation information");
        break;
      case 6:
        builder.setErrMsg("You need to reach level 2 to reply to private chat messages");
        break;
      case 7:
        builder.setErrMsg("userId or targetId data null");
        break;
      default:
        builder.setErrMsg("Message content is too long");
        break;
    }
    builder.setErrCode(1).setRecordId(0).setMessageId(request.getMessageId()).setTargetId(0);
    channel.writeAndFlush(
        new Packet(BasisOptCmd.CMD_CHAT, ChitchatCmd.SEND_MESSAGE,
            builder.build().toByteArray()));
    log.warn(">>>发送失败,{}", builder.getErrMsg());
  }


  /**
   * 离开聊天.
   *
   * @param channel 通讯管道
   * @param packet 数据包
   */
  @SuppressWarnings("unused")
  private void leaveChatRoom(Channel channel, Packet packet) {
    try {
      C10002.C100023c2s request = C10002.C100023c2s.parseFrom(packet.bytes);
      this.chatRepository.updateSingleChatStatus(packet.userId, request.getTargetId(), 0);
      channel.writeAndFlush(new Packet(BasisOptCmd.CMD_CHAT, ChitchatCmd.LEAVE_CHAT_ROOM,null));
      this.redisUtils.hdel(ChatKeyConstants.KEY_CHAT_SINGLE_LOGIN, StringUtils.nvl(packet.userId));
    } catch (Exception e) {
      log.error(e.getMessage());
      log.error(ExceptionUtil.getStackTrace(e));
    }
  }

  /**
   * 聆听语音.
   *
   * @param channel 管道信息
   * @param packet 数据包
   */
  private void listenVoice(Channel channel, Packet packet) {
    try {
      C10002.C100024c2s request = C10002.C100024c2s.parseFrom(packet.bytes);
      this.chatRepository.listenToVoice(request.getRecordId());
      channel.writeAndFlush(new Packet(BasisOptCmd.CMD_CHAT, ChitchatCmd.LISTEN_VOICE,null));
    } catch (Exception e) {
      log.error(e.getMessage());
      log.error(ExceptionUtil.getStackTrace(e));
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
    return Result.error();
  }

}
