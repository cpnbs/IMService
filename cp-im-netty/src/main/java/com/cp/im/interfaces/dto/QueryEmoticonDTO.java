package com.cp.im.interfaces.dto;

import javax.validation.constraints.NotNull;

/**
 * 查询聊天表情.
 */

public class QueryEmoticonDTO {

  @NotNull(message = "分类ID不能为空")
  private Integer categoryId;

  public Integer getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Integer categoryId) {
    this.categoryId = categoryId;
  }

  @Override
  public String toString() {
    return "QueryEmoticonDTO{"
        + "categoryId=" + categoryId
        + '}';
  }
}
