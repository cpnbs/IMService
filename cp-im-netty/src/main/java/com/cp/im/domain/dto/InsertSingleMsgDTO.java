package com.cp.im.domain.dto;

import com.cp.im.domain.entity.ChatSingleFile;

import java.time.LocalDateTime;

/**
 * 新增消息.
 *
 * @author wangcaiwen
 * @version v1.0.0
 * @since 2021/3/30 18:21
 */

public class InsertSingleMsgDTO {

  /** 用户ID. */
  private Long userId;
  /** 目标ID. */
  private Long targetId;
  /** 记录ID. */
  private Long recordId;
  /** 消息类型. */
  private Integer type;
  /** 消息内容. */
  private String message;
  /** 表情地址. */
  private String emojiUrl;
  /** 表情名称. */
  private String emoji;
  /** 屏蔽标记. */
  private Integer shield;
  /** 消息ID. */
  private String messageId;
  /** 是否已读 0 未读 1 已读. */
  private Integer messageRead;
  /** 发送时间. */
  private LocalDateTime sendTime;
  /** 聊天文件. */
  private ChatSingleFile messageFile;

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

  public Long getRecordId() {
    return recordId;
  }

  public void setRecordId(Long recordId) {
    this.recordId = recordId;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getEmojiUrl() {
    return emojiUrl;
  }

  public void setEmojiUrl(String emojiUrl) {
    this.emojiUrl = emojiUrl;
  }

  public String getEmoji() {
    return emoji;
  }

  public void setEmoji(String emoji) {
    this.emoji = emoji;
  }

  public Integer getShield() {
    return shield;
  }

  public void setShield(Integer shield) {
    this.shield = shield;
  }

  public String getMessageId() {
    return messageId;
  }

  public void setMessageId(String messageId) {
    this.messageId = messageId;
  }

  public Integer getMessageRead() {
    return messageRead;
  }

  public void setMessageRead(Integer messageRead) {
    this.messageRead = messageRead;
  }

  public LocalDateTime getSendTime() {
    return sendTime;
  }

  public void setSendTime(LocalDateTime sendTime) {
    this.sendTime = sendTime;
  }

  public ChatSingleFile getMessageFile() {
    return messageFile;
  }

  public void setMessageFile(ChatSingleFile messageFile) {
    this.messageFile = messageFile;
  }

  @Override
  public String toString() {
    return "InsertSingleMsgDTO{" +
            "userId=" + userId +
            ", targetId=" + targetId +
            ", recordId=" + recordId +
            ", type=" + type +
            ", message='" + message + '\'' +
            ", emojiUrl='" + emojiUrl + '\'' +
            ", emoji='" + emoji + '\'' +
            ", shield=" + shield +
            ", messageId='" + messageId + '\'' +
            ", messageRead=" + messageRead +
            ", sendTime=" + sendTime +
            ", messageFile=" + messageFile +
            '}';
  }
}
