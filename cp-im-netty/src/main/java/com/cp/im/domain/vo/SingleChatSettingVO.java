package com.cp.im.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 单聊聊天设置信息.
 *
 * @author wangcaiwen
 * @version v1.0.0
 * @since 2021/2/24 11:15
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SingleChatSettingVO {

  /** 好友ID. */
  private Long userId;
  /** 好友昵称. */
  private String nickName;
  /** 设置备注. */
  private String remark;
  /** 用户头像. */
  private String thumbIconURL;
  /** 好友性别. */
  private Integer sex;
  /** 用户角色. */
  private Integer role;
  /** 用户等级. */
  private Integer level;
  /** 标记置顶 [0否 1是]. */
  private Integer flagTop;
  /** 黑名单 [0否 1是]. */
  private Integer flagBlack;
  /** 是否好友 [0否 1是]. */
  private Integer isFriend;
  /** 免打扰 [0关闭 1开启]. */
  private Integer notDisturb;

  public SingleChatSettingVO() {
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public String getThumbIconURL() {
    return thumbIconURL;
  }

  public void setThumbIconURL(String thumbIconURL) {
    this.thumbIconURL = thumbIconURL;
  }

  public Integer getSex() {
    return sex;
  }

  public void setSex(Integer sex) {
    this.sex = sex;
  }

  public Integer getRole() {
    return role;
  }

  public void setRole(Integer role) {
    this.role = role;
  }

  public Integer getLevel() {
    return level;
  }

  public void setLevel(Integer level) {
    this.level = level;
  }

  public Integer getFlagTop() {
    return flagTop;
  }

  public void setFlagTop(Integer flagTop) {
    this.flagTop = flagTop;
  }

  public Integer getFlagBlack() {
    return flagBlack;
  }

  public void setFlagBlack(Integer flagBlack) {
    this.flagBlack = flagBlack;
  }

  public Integer getIsFriend() {
    return isFriend;
  }

  public void setIsFriend(Integer isFriend) {
    this.isFriend = isFriend;
  }

  public Integer getNotDisturb() {
    return notDisturb;
  }

  public void setNotDisturb(Integer notDisturb) {
    this.notDisturb = notDisturb;
  }

  @Override
  public String toString() {
    return "SingleChatSettingVO{"
        + "userId=" + userId
        + ", nickName='" + nickName + '\''
        + ", remark='" + remark + '\''
        + ", thumbIconURL='" + thumbIconURL + '\''
        + ", sex=" + sex
        + ", role=" + role
        + ", level=" + level
        + ", flagTop=" + flagTop
        + ", flagBlack=" + flagBlack
        + ", isFriend=" + isFriend
        + ", notDisturb=" + notDisturb
        + '}';
  }
}
