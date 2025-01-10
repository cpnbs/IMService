package com.cp.im.domain.dto;

/**
 * 单聊简略信息.
 *
 * @author wangcaiwen
 * @version v1.0.0
 * @since 2021/3/31 10:05
 */

public class ChatSimpleInfoDTO {
  /** 聊天状态. */
  private Integer chatStatus;
  /** 删除标记. */
  private Integer flagDelete;
  /** 置顶设置. */
  private Integer flagTop;
  /** 免打扰状态. */
  private Integer notDisturb;
  /** 未读数量. */
  private Integer unreadNum;

  public Integer getChatStatus() {
    return chatStatus;
  }

  public void setChatStatus(Integer chatStatus) {
    this.chatStatus = chatStatus;
  }

  public Integer getFlagDelete() {
    return flagDelete;
  }

  public void setFlagDelete(Integer flagDelete) {
    this.flagDelete = flagDelete;
  }

  public Integer getFlagTop() {
    return flagTop;
  }

  public void setFlagTop(Integer flagTop) {
    this.flagTop = flagTop;
  }

  public Integer getNotDisturb() {
    return notDisturb;
  }

  public void setNotDisturb(Integer notDisturb) {
    this.notDisturb = notDisturb;
  }

  public Integer getUnreadNum() {
    return unreadNum;
  }

  public void setUnreadNum(Integer unreadNum) {
    this.unreadNum = unreadNum;
  }

  @Override
  public String toString() {
    return "ChatSimpleInfoDTO{"
        + "chatStatus=" + chatStatus
        + ", flagDelete=" + flagDelete
        + ", flagTop=" + flagTop
        + ", notDisturb=" + notDisturb
        + ", unreadNum=" + unreadNum
        + '}';
  }
}
