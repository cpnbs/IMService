package com.cp.im.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 单聊信息.
 */

public class ChatSingle implements Serializable {
  private static final long serialVersionUID = -2299413860929898398L;
  /** 主键ID. */
  private Long id;
  /** 用户ID. */
  private Long userId;
  /** 目标ID. */
  private Long targetId;
  /** 分组ID. */
  private Integer divideId;
  /** 聊天状态 0 闲置中 1 聊天中. */
  private Integer chatStatus;
  /** 标记加星 0 否 1 是. */
  private Integer flagStar;
  /** 标记置顶 0 否 1 是. */
  private Integer flagTop;
  /** 标记移除 0 否 1 是. */
  private Integer flagDelete;
  /** 免打扰 0 关闭 1 开启. */
  private Integer notDisturb;
  /** 未读数量. */
  private Integer unreadNum;
  /** 消息记录ID. */
  private Long messageId;
  /** 消息时间. */
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime messageTime;
  /** 创建时间. */
  @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime createTime;
  /** 更新时间. */
  @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime updateTime;

  /** 别名. */
  private String alias;

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

  public Integer getChatStatus() {
    return chatStatus;
  }

  public void setChatStatus(Integer chatStatus) {
    this.chatStatus = chatStatus;
  }

  public Integer getDivideId() {
    return divideId;
  }

  public void setDivideId(Integer divideId) {
    this.divideId = divideId;
  }

  public Integer getFlagStar() {
    return flagStar;
  }

  public void setFlagStar(Integer flagStar) {
    this.flagStar = flagStar;
  }

  public Integer getFlagTop() {
    return flagTop;
  }

  public void setFlagTop(Integer flagTop) {
    this.flagTop = flagTop;
  }

  public Integer getFlagDelete() {
    return flagDelete;
  }

  public void setFlagDelete(Integer flagDelete) {
    this.flagDelete = flagDelete;
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

  public Long getMessageId() {
    return messageId;
  }

  public void setMessageId(Long messageId) {
    this.messageId = messageId;
  }

  public LocalDateTime getMessageTime() {
    return messageTime;
  }

  public void setMessageTime(LocalDateTime messageTime) {
    this.messageTime = messageTime;
  }

  public LocalDateTime getCreateTime() {
    return createTime;
  }

  public void setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
  }

  public LocalDateTime getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(LocalDateTime updateTime) {
    this.updateTime = updateTime;
  }

  public String getAlias() {
    return alias;
  }

  public void setAlias(String alias) {
    this.alias = alias;
  }

  public ChatSingle() {
  }

  @Override
  public String toString() {
    return "ChatSingle{"
        + "id=" + id
        + ", userId=" + userId
        + ", targetId=" + targetId
        + ", divideId=" + divideId
        + ", chatStatus=" + chatStatus
        + ", flagStar=" + flagStar
        + ", flagTop=" + flagTop
        + ", flagDelete=" + flagDelete
        + ", notDisturb=" + notDisturb
        + ", unreadNum=" + unreadNum
        + ", messageId=" + messageId
        + ", messageTime=" + messageTime
        + ", createTime=" + createTime
        + ", updateTime=" + updateTime
        + '}';
  }
}
