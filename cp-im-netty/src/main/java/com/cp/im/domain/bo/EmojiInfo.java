package com.cp.im.domain.bo;

/**
 * 表情信息.
 *
 * @author wangcaiwen
 * @version v1.0.0
 * @since 2021/2/24 6:19
 */

public class EmojiInfo {

  /** 表情名称. */
  private String emName;
  /** 表情动图. */
  private String animURL;

  public EmojiInfo() {
  }

  public EmojiInfo(String emName, String animURL) {
    this.emName = emName;
    this.animURL = animURL;
  }

  public String getEmName() {
    return emName;
  }

  public void setEmName(String emName) {
    this.emName = emName;
  }

  public String getAnimURL() {
    return animURL;
  }

  public void setAnimURL(String animURL) {
    this.animURL = animURL;
  }

  @Override
  public String toString() {
    return "EmojiInfoDTO{"
        + "emName='" + emName + '\''
        + ", animURL='" + animURL + '\''
        + '}';
  }
}
