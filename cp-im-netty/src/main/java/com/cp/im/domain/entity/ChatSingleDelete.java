package com.cp.im.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 聊天删除标记信息.
 *
 * @author wangcaiwen
 * @version v1.0.0
 * @since 2020/5/12 16:16
 */

public class ChatSingleDelete implements Serializable {
  private static final long serialVersionUID = -608045385003847488L;
  /** 主键ID. */
  private Long id;
  /** 用户ID. */
  private Long userId;
  /** 目标ID. */
  private Long targetId;
  /** 标记删除. */
  private Integer signNum;
  /** 创建时间. */
  @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createTime;
  /** 更新时间. */
  @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
  private Date updateTime;

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

  public Integer getSignNum() {
    return signNum;
  }

  public void setSignNum(Integer signNum) {
    this.signNum = signNum;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public Date getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }

  public ChatSingleDelete() {
  }

  @Override
  public String toString() {
    return "ChatSingleDelete{"
        + "id=" + id
        + ", userId=" + userId
        + ", targetId=" + targetId
        + ", signNum=" + signNum
        + ", createTime=" + createTime
        + ", updateTime=" + updateTime
        + '}';
  }
}
