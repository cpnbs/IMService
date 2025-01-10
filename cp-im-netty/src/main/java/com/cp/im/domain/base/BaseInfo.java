package com.cp.im.domain.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.validation.constraints.NotNull;

/**
 * 基础信息.
 *
 * @author wangcaiwen
 * @version v1.0.0
 * @since 2021/2/24 3:04
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseInfo extends BasePage {

  @NotNull(message = "用户ID不能为空")
  private Long userId;

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  @Override
  public String toString() {
    return "BaseInfo{" + super.toString()
        + ", userId=" + userId
        + '}';
  }
}
