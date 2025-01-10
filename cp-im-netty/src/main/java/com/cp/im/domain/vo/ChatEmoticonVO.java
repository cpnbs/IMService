package com.cp.im.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 聊天表情
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatEmoticonVO {

  /** 分类ID. */
  private Integer categoryId;
  /** 名称. */
  private String emName;
  /** 表情图片连接. */
  private String emUrl;
  /** 动态链接 */
  private String dongUrl;

  public ChatEmoticonVO() {
  }

  public Integer getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Integer categoryId) {
    this.categoryId = categoryId;
  }

  public String getEmName() {
    return emName;
  }

  public void setEmName(String emName) {
    this.emName = emName;
  }

  public String getEmUrl() {
    return emUrl;
  }

  public void setEmUrl(String emUrl) {
    this.emUrl = emUrl;
  }

  public String getDongUrl() {
    return dongUrl;
  }

  public void setDongUrl(String dongUrl) {
    this.dongUrl = dongUrl;
  }

  @Override
  public String toString() {
    return "ChatEmoticonVO{"
        + "categoryId=" + categoryId
        + ", emName='" + emName + '\''
        + ", emUrl='" + emUrl + '\''
        + ", dongUrl='" + emUrl + '\''
        + '}';
  }
}
