package com.cp.im.domain.dto;

/**
 * 更新展示信息.
 *
 * @author wangcaiwen
 * @version v1.0.0
 * @since 2021/3/31 10:39
 */

public class UpdateShowDTO {
  /** 用户ID. */
  private Long userId;
  /** 目标ID. */
  private Long targetId;
  /** 显示类型 0 是 1 否. */
  private Integer show;

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

  public Integer getShow() {
    return show;
  }

  public void setShow(Integer show) {
    this.show = show;
  }

  @Override
  public String toString() {
    return "UpdateShowDTO{"
        + "userId=" + userId
        + ", targetId=" + targetId
        + ", show=" + show
        + '}';
  }
}
