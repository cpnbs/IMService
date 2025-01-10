package com.cp.im.domain.dto;

/**
 * @author wangcaiwen
 * @version v1.0.0
 * @since 2021/5/3 0:26
 */
public class UpdateChatStatusDTO {

  /** 主键ID. */
  private Long id;
  /** 聊天状态 0 闲置中 1 聊天中. */
  private Integer chatStatus;
  /** 更新未读. */
  private Integer unreadNum;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getChatStatus() {
    return chatStatus;
  }

  public void setChatStatus(Integer chatStatus) {
    this.chatStatus = chatStatus;
  }

  public Integer getUnreadNum() {
    return unreadNum;
  }

  public void setUnreadNum(Integer unreadNum) {
    this.unreadNum = unreadNum;
  }

  @Override
  public String toString() {
    return "UpdateSingleStatusDTO{"
        + "id=" + id
        + ", chatStatus=" + chatStatus
        + ", unreadNum=" + unreadNum
        + '}';
  }
}
