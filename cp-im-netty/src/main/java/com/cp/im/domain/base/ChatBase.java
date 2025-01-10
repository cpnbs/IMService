package com.cp.im.domain.base;

/**
 * 聊天基础信息.
 *
 * @author wangcaiwen
 * @version v1.0.0
 * @since 2021/4/6 15:39
 */

public class ChatBase {
  /** 用户ID. */
  private Long userId;
  /** 目标ID. */
  private Long targetId;
  private Long isOrderChat;
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

  public Long getIsOrderChat() {
    return isOrderChat;
  }

  public void setIsOrderChat(Long isOrderChat) {
    this.isOrderChat = isOrderChat;
  }

  public ChatBase() {
  }

  public ChatBase(Long userId, Long targetId) {
    this.userId = userId;
    this.targetId = targetId;
  }

  public ChatBase(Long userId, Long targetId, Long isOrderChat) {
    this.userId = userId;
    this.targetId = targetId;
    this.isOrderChat = isOrderChat;
  }

  @Override
  public String toString() {
    return "ChatBase{" +
            "userId=" + userId +
            ", targetId=" + targetId +
            ", isOrderChat=" + isOrderChat +
            '}';
  }
}
