package com.cp.im.domain.dto;

import com.cp.im.domain.bo.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 聊天信息.
 *
 * @author wangcaiwen
 * @version v1.0.0
 * @since 2021/2/24 5:54
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SingleChatMsgDTO {

  /** 记录ID. */
  private Long recordId;
  /** 用户ID. */
  private Long userId;
  /** 消息内容 [=文本内容]. */
  private String message;
  /** 消息唯一标识符. */
  private String messageId;
  /** 消息类型 [0 文本 1 Emoji 2 图片 3 语音 4 视频]. */
  private Integer messageType;
  /** 是否操作 [0 否 1 是] 用于标记音频文件. */
  private Integer isAction;
  /** 表情地址. */
  private String emojiUrl;
  /** 表情名称. */
  private String emojiName;
  /** 表情信息. */
  private EmojiInfo emojiInfo;
  /** 图片信息. */
  private ImageInfo imageInfo;
  /** 语音信息. */
  private VoiceInfo voiceInfo;
  /** 视频信息. */
  private VideoInfo videoInfo;

  /** 消息时间. */
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime createTime;

  private Long createTimestamp;

  public SingleChatMsgDTO() {
  }

  public Long getRecordId() {
    return recordId;
  }

  public void setRecordId(Long recordId) {
    this.recordId = recordId;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getMessageId() {
    return messageId;
  }

  public void setMessageId(String messageId) {
    this.messageId = messageId;
  }

  public Integer getMessageType() {
    return messageType;
  }

  public void setMessageType(Integer messageType) {
    this.messageType = messageType;
  }

  public Integer getIsAction() {
    return isAction;
  }

  public void setIsAction(Integer isAction) {
    this.isAction = isAction;
  }

  public String getEmojiUrl() {
    return emojiUrl;
  }

  public void setEmojiUrl(String emojiUrl) {
    this.emojiUrl = emojiUrl;
  }

  public String getEmojiName() {
    return emojiName;
  }

  public void setEmojiName(String emojiName) {
    this.emojiName = emojiName;
  }

  public EmojiInfo getEmojiInfo() {
    return emojiInfo;
  }

  public void setEmojiInfo(EmojiInfo emojiInfo) {
    this.emojiInfo = emojiInfo;
  }

  public ImageInfo getImageInfo() {
    return imageInfo;
  }

  public void setImageInfo(ImageInfo imageInfo) {
    this.imageInfo = imageInfo;
  }

  public VoiceInfo getVoiceInfo() {
    return voiceInfo;
  }

  public void setVoiceInfo(VoiceInfo voiceInfo) {
    this.voiceInfo = voiceInfo;
  }

  public VideoInfo getVideoInfo() {
    return videoInfo;
  }

  public void setVideoInfo(VideoInfo videoInfo) {
    this.videoInfo = videoInfo;
  }

  public LocalDateTime getCreateTime() {
    return createTime;
  }

  public void setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
  }

  @Override
  public String toString() {
    return "SingleChatMsgDTO{" +
            "recordId=" + recordId +
            ", userId=" + userId +
            ", message='" + message + '\'' +
            ", messageId='" + messageId + '\'' +
            ", messageType=" + messageType +
            ", isAction=" + isAction +
            ", emojiUrl='" + emojiUrl + '\'' +
            ", emojiName='" + emojiName + '\'' +
            ", emojiInfo=" + emojiInfo +
            ", imageInfo=" + imageInfo +
            ", voiceInfo=" + voiceInfo +
            ", videoInfo=" + videoInfo +
            ", createTime=" + createTime +
            '}';
  }

  public Long getCreateTimestamp() {
    return createTimestamp;
  }

  public void setCreateTimestamp(Long createTimestamp) {
    this.createTimestamp = createTimestamp;
  }
}
