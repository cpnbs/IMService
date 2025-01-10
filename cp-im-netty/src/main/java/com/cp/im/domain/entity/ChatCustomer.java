package com.cp.im.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 客服聊天记录.
 *
 * @author wangcaiwen
 * @version v1.0.0
 * @since 2021/7/1 12:57
 */

public class ChatCustomer implements Serializable {

  private static final long serialVersionUID = 7889547162943036974L;

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

  public LocalDateTime getCreateTime() {
    return createTime;
  }

  public void setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
  }

  @Override
  public String toString() {
    return "ChatCustomer{"
        + "id=" + id
        + ", userId=" + userId
        + ", targetId=" + targetId
        + ", message='" + message + '\''
        + ", messageId='" + messageId + '\''
        + ", createTime=" + createTime
        + '}';
  }
}
