package com.cp.im.domain.dto;

import java.time.LocalDateTime;

/**
 * 更新消息.
 *
 * @author wangcaiwen
 * @version v1.0.0
 * @since 2021/3/30 20:08
 */

public class UpdateSingleMsgDTO {
  /** 用户ID. */
  private Long userId;
  /** 目标ID. */
  private Long targetId;
  /** 记录ID. */
  private Long recordId;
  /** 阅读类型. */
  private Integer readType;
  /** 发送时间. */
  private LocalDateTime sendTime;
  /** 来源. */
  private Integer source;
  /** 屏蔽标记. */
  private Integer shield;

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

  public Integer getReadType() {
    return readType;
  }

  public void setReadType(Integer readType) {
    this.readType = readType;
  }

  public LocalDateTime getSendTime() {
    return sendTime;
  }

  public void setSendTime(LocalDateTime sendTime) {
    this.sendTime = sendTime;
  }

  public Integer getSource() {
    return source;
  }

  public void setSource(Integer source) {
    this.source = source;
  }

  public Integer getShield() {
    return shield;
  }

  public void setShield(Integer shield) {
    this.shield = shield;
  }

  @Override
  public String toString() {
    return "UpdateMsgDTO{"
        + "userId=" + userId
        + ", targetId=" + targetId
        + ", recordId=" + recordId
        + ", readType=" + readType
        + ", sendTime=" + sendTime
        + ", source=" + source
        + ", shield=" + shield
        + '}';
  }
}
