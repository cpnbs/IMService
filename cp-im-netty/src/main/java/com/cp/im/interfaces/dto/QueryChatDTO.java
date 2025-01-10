package com.cp.im.interfaces.dto;

import com.cp.im.domain.base.BasePage;
import com.fasterxml.jackson.annotation.JsonInclude;
import javax.validation.constraints.NotNull;

/**
 * 单聊查询信息.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class QueryChatDTO extends BasePage {
  /** 用户ID. */
  @NotNull(message = "用户ID不能为空")
  private Long userId;
  /** 用户ID. */
  @NotNull(message = "目标ID不能为空")
  private Long targetId;
  /** 标记时间. */
  private String indexTime;

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

  public String getIndexTime() {
    return indexTime;
  }

  public void setIndexTime(String indexTime) {
    this.indexTime = indexTime;
  }

  @Override
  public String toString() {
    return "QueryChatDTO{" +
        "userId=" + userId +
        ", targetId=" + targetId +
        ", indexTime='" + indexTime + '\'' +
        '}';
  }
}
