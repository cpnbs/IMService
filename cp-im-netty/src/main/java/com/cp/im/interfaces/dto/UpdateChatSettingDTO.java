package com.cp.im.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.validation.constraints.NotNull;

/**
 * 更新聊天设置.
 *
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateChatSettingDTO {

  /** 用户ID. */
  @NotNull(message = "用户ID不能为空")
  private Long userId;
  /** 群聊ID. */
  private Long groupId;
  /** 好友ID[指单聊]. */
  @NotNull(message = "目标ID不能为空")
  private Long targetId;
  /** 分组ID. */
  private Integer divideId;
  /** 标记加星 [0否 1是] . */
  private Integer flagStar;
  /** 标记置顶 [0否 1是] . */
  private Integer flagTop;
  /** 标记移除 [0否 1是]. */
  private Integer flagDelete;
  /** 免打扰 [0关闭 1开启] . */
  private Integer notDisturb;
  /** 别名 备注. */
  private String alias;

  public Long getUserId() {
    return userId;
  }
  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getGroupId() {
    return groupId;
  }
  public void setGroupId(Long groupId) {
    this.groupId = groupId;
  }

  public Long getTargetId() {
    return targetId;
  }
  public void setTargetId(Long targetId) {
    this.targetId = targetId;
  }

  public Integer getDivideId() {
    return divideId;
  }
  public void setDivideId(Integer divideId) {
    this.divideId = divideId;
  }

  public Integer getFlagStar() {
    return flagStar;
  }
  public void setFlagStar(Integer flagStar) {
    this.flagStar = flagStar;
  }

  public Integer getFlagTop() {
    return flagTop;
  }
  public void setFlagTop(Integer flagTop) {
    this.flagTop = flagTop;
  }

  public Integer getFlagDelete() {
    return flagDelete;
  }
  public void setFlagDelete(Integer flagDelete) {
    this.flagDelete = flagDelete;
  }

  public Integer getNotDisturb() {
    return notDisturb;
  }
  public void setNotDisturb(Integer notDisturb) {
    this.notDisturb = notDisturb;
  }

  public String getAlias() {
    return alias;
  }
  public void setAlias(String alias) {
    this.alias = alias;
  }

  @Override
  public String toString() {
    return "UpdateChatSettingDTO{" +
        "userId=" + userId +
        ", groupId=" + groupId +
        ", targetId=" + targetId +
        ", divideId=" + divideId +
        ", flagStar=" + flagStar +
        ", flagTop=" + flagTop +
        ", flagDelete=" + flagDelete +
        ", notDisturb=" + notDisturb +
        '}';
  }
}
