package com.cp.im.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.validation.constraints.NotNull;

/**
 * 删除聊天记录.
 *
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeleteChatMsgDTO {

  /** 用户ID. */
  @NotNull(message = "用户ID不能为空")
  private Long userId;
  /** 目标ID. */
  @NotNull(message = "目标ID不能为空")
  private Long targetId;

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

  @Override
  public String toString() {
    return "DeleteChatMsgDTO{" +
        "userId=" + userId +
        ", targetId=" + targetId +
        '}';
  }
}
