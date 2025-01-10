package com.cp.im.domain.repository;

import com.cp.im.domain.base.ChatBase;
import com.cp.im.domain.base.UpdateBase;
import com.cp.im.domain.dto.InsertSingleMsgDTO;
import com.cp.im.domain.dto.UpdateSingleMsgDTO;
import com.cp.im.domain.dto.UpdateChatStatusDTO;
import com.cp.im.domain.entity.PicInfo;
import com.cp.im.domain.entity.ChatSingle;
import com.cp.im.domain.entity.ChatSingleMessage;
import com.cp.im.domain.entity.ChatSingleDelete;
import com.cp.im.infrastructure.codec.FeignMultipartFile;
import com.cp.im.infrastructure.file.FileService;
import com.cp.im.infrastructure.mapper.ChatSingleDeleteMapper;
import com.cp.im.infrastructure.mapper.ChatSingleMapper;
import com.cp.im.infrastructure.mapper.ChatSingleMessageMapper;
import com.cp.im.infrastructure.mapper.ChatSingleFileMapper;
import com.cp.im.proto.c10002msg.C10002;
import com.cp.im.result.Result;
import com.cp.im.utils.ExceptionUtil;
import com.cp.im.utils.StringUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * 聊天数据管理.
 */
@Slf4j
@Component
public class ChatRepository {

  @Autowired
  private FileService fileService;

  @Autowired
  private ChatSingleMapper chatSingleMapper;

  @Autowired
  private ChatSingleMessageMapper chatSingleMessageMapper;

  @Autowired
  private ChatSingleFileMapper chatSingleFileMapper;

  @Resource
  private ChatSingleDeleteMapper chatSingleDeleteMapper;

  public String getToken(Long userId) {
    return this.chatSingleMapper.getToken(userId);
  }

  public List<Long> getTargetList(Long userId) {
    return this.chatSingleMapper.getTargetList(userId);
  }

  /**
   * 关系判断.
   *
   * @param userId   用户ID
   * @param targetId 目标ID
   */
  public void buildRelation(Long userId, Long targetId) {
    Integer check1 = this.chatSingleMapper.checkUserRelation(userId, targetId);
    Integer check2 = this.chatSingleMapper.checkUserRelation(targetId, userId);
    LocalDateTime nowTime = LocalDateTime.now();
    if (check1 == 1 && check2 == 1) {
      return;
    }

    if (check1 == 0 && check2 == 0) {
      List<ChatSingle> userList = Lists.newLinkedList();
      long recordId1 = newAddMessage(userId, targetId, "Add friend successfully");
      if (recordId1 > 0) {
        ChatSingle chatLink1 = new ChatSingle();
        chatLink1.setUserId(userId);
        chatLink1.setTargetId(targetId);
        chatLink1.setFlagDelete(1);
        chatLink1.setUnreadNum(0);
        chatLink1.setMessageId(recordId1);
        chatLink1.setMessageTime(nowTime);
        userList.add(chatLink1);
      }
      long recordId2 = newAddMessage(targetId, userId, "Hi, I added you as a friend！");
      if (recordId2 > 0) {
        ChatSingle chatLink2 = new ChatSingle();
        chatLink2.setUserId(targetId);
        chatLink2.setTargetId(userId);
        chatLink2.setFlagDelete(1);
        chatLink2.setUnreadNum(0);
        chatLink2.setMessageId(recordId2);
        chatLink2.setMessageTime(nowTime);
        userList.add(chatLink2);
      }
      this.chatSingleMapper.newUserChatLink(userList);
      this.chatSingleDeleteMapper.insertMessage(messageRelationList(userId, targetId));
      return;
    }
    Long recordId = newAddMessage(userId, targetId, "Add friend successfully");
    // 关系一建立.永不删除
    Map<String, Object> messageMap = Maps.newHashMap();
    messageMap.put("userId", userId);
    messageMap.put("linkId", targetId);
    messageMap.put("unreadNum", 0);
    messageMap.put("messageId", recordId);
    messageMap.put("messageTime", nowTime);
    this.chatSingleMapper.updateUserChatSetting(messageMap);
    ChatSingleDelete deleteIndex = new ChatSingleDelete();
    deleteIndex.setUserId(userId);
    deleteIndex.setTargetId(targetId);
    insertChatMessageRelation(deleteIndex);
  }

  /**
   * 关联列表.
   *
   * @param userId   用户ID
   * @param targetId 目标ID
   * @return 关联列表
   */
  private List<Map<String, Object>> messageRelationList(Long userId, Long targetId) {
    List<Map<String, Object>> messageRelationList = new LinkedList<>();
    Map<String, Object> newResult = Maps.newHashMap();
    newResult.put("userId", userId);
    newResult.put("targetId", targetId);
    messageRelationList.add(newResult);
    newResult = Maps.newHashMap();
    newResult.put("userId", targetId);
    newResult.put("targetId", userId);
    messageRelationList.add(newResult);
    return messageRelationList;
  }

  public void insertChatMessageRelation(ChatSingleDelete insertInfo) {
    Integer isExists = this.chatSingleDeleteMapper
            .isExists(insertInfo.getUserId(), insertInfo.getTargetId());
    if (isExists == 0) {
      this.chatSingleDeleteMapper.insertChatMessageRelation(insertInfo);
    }
  }

  public Long newAddMessage(Long userId, Long targetId, String msg) {
    ChatSingleMessage chatSingleMessage = new ChatSingleMessage();
    chatSingleMessage.setUserId(userId);
    chatSingleMessage.setTargetId(targetId);
    String messageId = userId + "_to_" + targetId + "_" + Instant.now().toEpochMilli();
    chatSingleMessage.setMessageId(messageId);
    chatSingleMessage.setMessage(msg);
    chatSingleMessage.setMessageType(0);
    chatSingleMessage.setMessageRead(1);
    chatSingleMessage.setMessageAction(1);
    chatSingleMessage.setCreateTime(LocalDateTime.now());
    this.chatSingleMessageMapper.insertSingleMessage(chatSingleMessage);
    return chatSingleMessage.getId();
  }

  /**
   * 单聊-新增聊天信息.
   *
   * @param insertInfo    新增信息
   * @return 新增结果
   */
  public Long saveSingleChatGetRecordId(InsertSingleMsgDTO insertInfo) {
    ChatSingleMessage messageInfo = new ChatSingleMessage();
    messageInfo.setUserId(insertInfo.getUserId());
    messageInfo.setTargetId(insertInfo.getTargetId());
    messageInfo.setMessageType(insertInfo.getType());
    if (messageInfo.getMessageType() == 0) {
      messageInfo.setMessage(insertInfo.getMessage());
    }
    if (messageInfo.getMessageType() == 1) {
      messageInfo.setMessageEmojiUrl(insertInfo.getEmojiUrl());
      messageInfo.setMessageEmojiName(insertInfo.getEmoji());
    }
    messageInfo.setMessageId(insertInfo.getMessageId());
    messageInfo.setMessageRead(insertInfo.getMessageRead());
    if (messageInfo.getMessageType() == 3) {
      messageInfo.setMessageAction(0);
    }
    messageInfo.setCreateTime(insertInfo.getSendTime());
    this.chatSingleMessageMapper.insertSingleMessage(messageInfo);
    Long recordId = messageInfo.getId();
    if (Objects.nonNull(recordId) && recordId > 0) {
      insertInfo.setRecordId(recordId);
      // 0 文本 1 Emoji 2 图片 3 语音 4 视频
      if (messageInfo.getMessageType() >= 2 && messageInfo.getMessageType() < 5) {
        if (Objects.nonNull(insertInfo.getMessageFile())) {
          if (StringUtils.isEmpty(insertInfo.getMessageFile().getFileUrl())) {
            this.chatSingleMessageMapper.deleteMessage(recordId);
            return 0l;
          }
          insertInfo.getMessageFile().setRecordId(recordId);
          this.chatSingleFileMapper.insertFile(insertInfo.getMessageFile());
          return recordId;
        }
        this.chatSingleMessageMapper.deleteMessage(recordId);
        return 0l;
      }
      return recordId;
    }
    return 0l;
  }

  /**
   * 更新聊天状态.
   *
   * @param userId   用户ID
   * @param targetId 目标ID
   * @param status   聊天状态
   */
  public void updateSingleChatStatus(Long userId, Long targetId, Integer status) {
    UpdateBase base = this.chatSingleMapper.getUpdateBase(userId, targetId);
    if (Objects.nonNull(base)) {
      UpdateChatStatusDTO updateStatus = new UpdateChatStatusDTO();
      updateStatus.setId(base.getId());
      updateStatus.setChatStatus(status);
      if (status == 1) {
        updateStatus.setUnreadNum(0);
      }
      this.chatSingleMapper.updateStatus(updateStatus);
    }
  }

  public void updateUserChatStatusAll(Long userId) {
    this.chatSingleMapper.updateUserChatStatusAll(userId);
  }

  public Map<String, Object> getUserBase(Long userId, Long targetId) {
    return this.chatSingleMapper.getUserBase(userId, targetId);
  }


  /**
   * 单聊/私聊-聊天状态.
   *
   * @param userId   用户ID
   * @param targetId 目标ID
   * @return 聊天状态
   */
  public Integer targetUserChatStatus(Long userId, Long targetId) {
    ChatBase queryInfo = new ChatBase(userId, targetId);
    return this.chatSingleMapper.queryStatus(queryInfo);
  }

  public void listenToVoice(Long recordId) {
    this.chatSingleMessageMapper.updateMessageAction(recordId);
  }

  /**
   * 更新消息-单聊.
   *
   * @param insertInfo  消息信息
   * @param readType 阅读类型
   */
  public void updateSingleChatNoticeList(InsertSingleMsgDTO insertInfo, Integer readType, Integer source) {

    UpdateSingleMsgDTO updateInfo = new UpdateSingleMsgDTO();
    updateInfo.setUserId(insertInfo.getUserId());
    updateInfo.setTargetId(insertInfo.getTargetId());
    updateInfo.setRecordId(insertInfo.getRecordId());
    updateInfo.setSendTime(insertInfo.getSendTime());
    updateInfo.setReadType(readType);
    updateInfo.setSource(source);

    if (updateInfo.getReadType() == 0) {
      Long id = this.chatSingleMapper.getPrimaryKeyId(updateInfo.getUserId(), updateInfo.getTargetId());
      ChatSingle chatInfo = new ChatSingle();
      chatInfo.setId(id);
      chatInfo.setMessageId(updateInfo.getRecordId());
      chatInfo.setMessageTime(updateInfo.getSendTime());
      chatInfo.setUnreadNum(0);
      chatInfo.setUserId(updateInfo.getUserId());
      chatInfo.setTargetId(updateInfo.getTargetId());
      chatInfo.setFlagDelete(0);
      log.warn("[INFO] 类型0A={}", chatInfo);
      this.chatSingleMapper.updateBriefInfo(chatInfo);

      id = this.chatSingleMapper.getPrimaryKeyId(updateInfo.getTargetId(), updateInfo.getUserId());
      Integer unreadNum = this.chatSingleMapper.getUnreadNumber(id);
      chatInfo = new ChatSingle();
      chatInfo.setId(id);
      chatInfo.setUserId(updateInfo.getTargetId());
      chatInfo.setTargetId(updateInfo.getUserId());
      chatInfo.setMessageId(updateInfo.getRecordId());
      chatInfo.setMessageTime(updateInfo.getSendTime());
      chatInfo.setUnreadNum(unreadNum + 1);
      chatInfo.setFlagDelete(0);
      log.warn("[INFO] 类型0B={}", chatInfo);
      this.chatSingleMapper.updateBriefInfo(chatInfo);

    } else {
      Long id = this.chatSingleMapper.getPrimaryKeyId(updateInfo.getUserId(), updateInfo.getTargetId());
      ChatSingle chatInfo = new ChatSingle();
      chatInfo.setId(id);
      chatInfo.setMessageId(updateInfo.getRecordId());
      chatInfo.setMessageTime(updateInfo.getSendTime());
      chatInfo.setUnreadNum(0);
      chatInfo.setUserId(updateInfo.getUserId());
      chatInfo.setTargetId(updateInfo.getTargetId());
      chatInfo.setFlagDelete(0);
      log.warn("[INFO] 类型1A={}", chatInfo);
      this.chatSingleMapper.updateBriefInfo(chatInfo);

      id = this.chatSingleMapper.getPrimaryKeyId(updateInfo.getTargetId(), updateInfo.getUserId());
      chatInfo = new ChatSingle();
      chatInfo.setId(id);
      chatInfo.setMessageId(updateInfo.getRecordId());
      chatInfo.setMessageTime(updateInfo.getSendTime());
      chatInfo.setUnreadNum(0);
      chatInfo.setUserId(updateInfo.getTargetId());
      chatInfo.setTargetId(updateInfo.getUserId());
      chatInfo.setFlagDelete(0);
      log.warn("[INFO] 类型1B={}", chatInfo);
      this.chatSingleMapper.updateBriefInfo(chatInfo);
    }
  }

  /**
   * 表情信息.
   *
   * @param insertInfo 聊天信息
   * @return ESEmojiInfo
   */
  public C10002.ESEmojiInfo.Builder emojiBuilder(InsertSingleMsgDTO insertInfo) {
    C10002.ESEmojiInfo.Builder emojiInfo = C10002.ESEmojiInfo.newBuilder();
    if (Objects.nonNull(insertInfo)) {
      emojiInfo.setEmName(insertInfo.getEmoji());
      emojiInfo.setAnimURL(insertInfo.getEmojiUrl());
    }
    return emojiInfo;
  }

  /**
   * 图片信息.
   *
   * @param insertInfo 图片信息
   * @return ESImageInfo
   */
  public C10002.ESImageInfo.Builder imageBuilder(InsertSingleMsgDTO insertInfo) {
    C10002.ESImageInfo.Builder imageInfo = C10002.ESImageInfo.newBuilder();
    if (Objects.nonNull(insertInfo)) {
      if (Objects.nonNull(insertInfo.getMessageFile())) {
        imageInfo.setWidth(insertInfo.getMessageFile().getFileWidth());
        imageInfo.setHeight(insertInfo.getMessageFile().getFileHeight());
        imageInfo.setImageURL(insertInfo.getMessageFile().getFileUrl() + "?p=0");
        imageInfo.setLitimg(insertInfo.getMessageFile().getFileUrl() + "?w=400");
      }
    }
    return imageInfo;
  }

  /**
   * 语音信息.
   *
   * @param insertInfo 语音信息
   * @return ESVoiceInfo
   */
  public C10002.ESVoiceInfo.Builder voiceBuilder(InsertSingleMsgDTO insertInfo) {
    C10002.ESVoiceInfo.Builder voiceInfo = C10002.ESVoiceInfo.newBuilder();
    if (Objects.nonNull(insertInfo)) {
      if (Objects.nonNull(insertInfo.getMessageFile())) {
        voiceInfo.setDuration(insertInfo.getMessageFile().getFileDuration());
        voiceInfo.setVoiceURL(insertInfo.getMessageFile().getFileUrl());
      }
    }
    return voiceInfo;
  }

  /**
   * 视频信息.
   *
   * @param insertInfo 视频信息
   * @return ESVideoInfo
   */
  public C10002.ESVideoInfo.Builder videoBuilder(InsertSingleMsgDTO insertInfo) {
    C10002.ESVideoInfo.Builder videoInfo = C10002.ESVideoInfo.newBuilder();
    if (Objects.nonNull(insertInfo)) {
      if (Objects.nonNull(insertInfo.getMessageFile())) {
        videoInfo.setWidth(insertInfo.getMessageFile().getFileWidth());
        videoInfo.setHeight(insertInfo.getMessageFile().getFileHeight());
        videoInfo.setDuration(insertInfo.getMessageFile().getFileDuration());
        videoInfo.setVideoURL(insertInfo.getMessageFile().getFileUrl());
        videoInfo.setCoverURL(insertInfo.getMessageFile().getFileCoverUrl());
      }
    }
    return videoInfo;
  }

  /**
   * 文件上传.
   *
   * @param request 文件信息
   * @return 上传回调
   */
  public Map<String, Object> fileUpload(C10002.C100021c2s request, Integer sort, Long target) {
    Map<String, Object> fileResult = null;
    try {
      String folder = (sort == 0 ? "chat/single/" + target : "chat/group/" + target) + "/";
      switch (request.getMessageType()) {
        case 2:
          C10002.ESImageInfo imageInfo = request.getImageInfo();
          String imageExt = "." + imageInfo.getImageExt();

          Result<PicInfo> imageResult = null;
          if (imageExt.equals(".net") && StringUtils.isLong(imageInfo.getImageURL())){ //web端特殊处理
            imageResult = this.fileService.getFile(Long.parseLong(imageInfo.getImageURL()));
          } else {
            byte[] imageByte = imageInfo.getImageFile().toByteArray();
            InputStream inputStreamImage = new ByteArrayInputStream(imageByte);
            MultipartFile imageFile = new FeignMultipartFile("file", getRandomFileName().concat(imageExt),
                    ContentType.APPLICATION_OCTET_STREAM.toString(), inputStreamImage);
            imageResult = this.fileService.asyncUploadOssFile(imageFile,
                    StringUtils.nvl(imageInfo.getHeight()), StringUtils.nvl(imageInfo.getWidth()), folder, null);
          }

          if (Objects.nonNull(imageResult)) {
            fileResult = Maps.newHashMap();
            fileResult.put("fileUrl", imageResult.getData().getPicUrl());
          }
          break;
        case 3:
          C10002.ESVoiceInfo voiceInfo = request.getVoiceInfo();
          String voiceExt = "." + voiceInfo.getVoiceExt();
          byte[] voiceByte = voiceInfo.getVoiceFile().toByteArray();
          InputStream inputStreamVoice = new ByteArrayInputStream(voiceByte);
          MultipartFile voiceFile = new FeignMultipartFile("file", getRandomFileName().concat(voiceExt),
                  ContentType.APPLICATION_OCTET_STREAM.toString(), inputStreamVoice);
          Result<PicInfo> voiceResult = this.fileService.asyncUploadOssFile(voiceFile, "", "", folder, null);
          if (Objects.nonNull(voiceResult)) {
            fileResult = Maps.newHashMap();
            fileResult.put("fileUrl", voiceResult.getData().getPicUrl());
          }
          break;
        default:
          C10002.ESVideoInfo videoInfo = request.getVideoInfo();
          String videoExt = "." + videoInfo.getVideoExt();
          byte[] videoByte = videoInfo.getVideoFile().toByteArray();
          String coverExt = "." + videoInfo.getCoverExt();
          byte[] coverByte = videoInfo.getCoverFile().toByteArray();
          InputStream inputStreamVideo = new ByteArrayInputStream(videoByte);
          InputStream inputStreamCover = new ByteArrayInputStream(coverByte);
          MultipartFile videoFile = new FeignMultipartFile("file", getRandomFileName().concat(videoExt),
                  ContentType.APPLICATION_OCTET_STREAM.toString(), inputStreamVideo);
          MultipartFile coverFile = new FeignMultipartFile("file", getRandomFileName().concat(coverExt),
                  ContentType.APPLICATION_OCTET_STREAM.toString(), inputStreamCover);
          Result<PicInfo> videoResult = this.fileService.asyncUploadOssFile(videoFile,
                  StringUtils.nvl(videoInfo.getHeight()), StringUtils.nvl(videoInfo.getWidth()), folder, null);
          Result<PicInfo> coverResult = this.fileService.asyncUploadOssFile(coverFile, "", "", folder, null);
          if (Objects.nonNull(videoResult) && Objects.nonNull(coverResult)) {
            fileResult = Maps.newHashMap();
            fileResult.put("fileUrl", videoResult.getData().getPicUrl());
            fileResult.put("coverUrl", coverResult.getData().getPicUrl());
          }
          break;
      }
    } catch (Exception e) {
      log.error(e.getMessage());
      log.error(ExceptionUtil.getStackTrace(e));
    }
    return fileResult;
  }

  /**
   * 获得随机文件名称.
   *
   * @return 文件名称
   */
  private String getRandomFileName() {
    return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
  }


}
