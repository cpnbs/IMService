package com.cp.im.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 聊天信息.
 *
 * @author wangcaiwen
 * @version v1.0.0
 * @since 2020/4/13 15:13
 */

public class ChatSingleMessage implements Serializable {
  private static final long serialVersionUID = 6791981980744760875L;
  /** 主键ID. */
  private Long id;
  /** 用户ID. */
  private Long userId;
  /** 目标ID. */
  private Long targetId;
  /** 消息内容. */
  private String message;
  /** 消息唯一标识符. */
  private String messageId;
  /** 消息类型 0 文本 1 Emoji 2 图片 3 语音 4 视频 */
  private Integer messageType;
  /** 是否已读 0 未读 1 已读. */
  private Integer messageRead;
  /** 是否操作(用于标记音频文件) 0 否 1 是. */
  private Integer messageAction;
  /** 消息屏蔽 [0 否 1 是]. */
  private Integer messageShield;
  /** 表情地址. */
  private String messageEmojiUrl;
  /** 表情名称. */
  private String messageEmojiName;
  /** 创建时间. */
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime createTime;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getTargetId() {
    return targetId;
  }

  public void setTargetId(Long targetId) {
    this.targetId = targetId;
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

  public Integer getMessageRead() {
    return messageRead;
  }

  public void setMessageRead(Integer messageRead) {
    this.messageRead = messageRead;
  }

  public Integer getMessageAction() {
    return messageAction;
  }

  public void setMessageAction(Integer messageAction) {
    this.messageAction = messageAction;
  }

  public Integer getMessageShield() {
    return messageShield;
  }

  public void setMessageShield(Integer messageShield) {
    this.messageShield = messageShield;
  }

  public String getMessageEmojiUrl() {
    return messageEmojiUrl;
  }

  public void setMessageEmojiUrl(String messageEmojiUrl) {
    this.messageEmojiUrl = messageEmojiUrl;
  }

  public String getMessageEmojiName() {
    return messageEmojiName;
  }

  public void setMessageEmojiName(String messageEmojiName) {
    this.messageEmojiName = messageEmojiName;
  }

  public LocalDateTime getCreateTime() {
    return createTime;
  }

  public void setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
  }

  public ChatSingleMessage() {
  }

  @Override
  public String toString() {
    return "ChatSingleMessage{"
        + "id=" + id
        + ", userId=" + userId
        + ", targetId=" + targetId
        + ", message='" + message + '\''
        + ", messageId='" + messageId + '\''
        + ", messageType=" + messageType
        + ", messageRead=" + messageRead
        + ", messageAction=" + messageAction
        + ", messageShield=" + messageShield
        + ", messageEmojiUrl='" + messageEmojiUrl + '\''
        + ", messageEmojiName='" + messageEmojiName + '\''
        + ", createTime=" + createTime
        + '}';
  }
}
