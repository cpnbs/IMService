package com.cp.im.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 聊天分拣信息.
 *
 * @author wangcaiwen
 * @version v1.0.0
 * @since 2021/2/23 13:39
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatSortDTO {

  /** 聊天分类 [1单聊 2群聊]. */
  private Integer sort;
  /** 聊天ID[指群聊ID]. */
  private Long groupId;
  /** 好友ID[指单聊]. */
  private Long targetId;
  /** 分组ID. */
  private Integer divideId;
  /** 聊天图标. */
  private String thumbIconURL;
  /** 聊天账号. */
  private String mobile;
  /** 聊天别称. */
  private String alias;
  /** 消息加星. */
  private Integer flagStar;
  /** 消息置顶. */
  private Integer flagTop;
  /** 免打扰 [0关闭 1开启]. */
  private Integer notDisturb;
  /** 未读数量. */
  private Integer unreadNum;
  /** 最后的消息. */
  private String message;
  /** 用户名称[指群聊]. */
  private String messageUser;
  /** 消息分类. */
  private Integer messageType;
  /** 消息时间. */
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime lastTime;

  private Long lastTimestamp;

  /**
   * 筛选使用的id
   */
  private Long filterId;

  public Long getFilterId() {
    return filterId;
  }

  public void setFilterId(Long filterId) {
    this.filterId = filterId;
  }

  public Integer getSort() {
    return sort;
  }

  public void setSort(Integer sort) {
    this.sort = sort;
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

  public String getThumbIconURL() {
    return thumbIconURL;
  }

  public void setThumbIconURL(String thumbIconURL) {
    this.thumbIconURL = thumbIconURL;
  }

  public String getAlias() {
    return alias;
  }

  public void setAlias(String alias) {
    this.alias = alias;
  }

  public Integer getFlagTop() {
    return flagTop;
  }

  public void setFlagTop(Integer flagTop) {
    this.flagTop = flagTop;
  }

  public Integer getNotDisturb() {
    return notDisturb;
  }

  public void setNotDisturb(Integer notDisturb) {
    this.notDisturb = notDisturb;
  }

  public Integer getUnreadNum() {
    return unreadNum;
  }

  public void setUnreadNum(Integer unreadNum) {
    this.unreadNum = unreadNum;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getMessageUser() {
    return messageUser;
  }

  public void setMessageUser(String messageUser) {
    this.messageUser = messageUser;
  }

  public Integer getMessageType() {
    return messageType;
  }

  public void setMessageType(Integer messageType) {
    this.messageType = messageType;
  }

  public LocalDateTime getLastTime() {
    return lastTime;
  }

  public void setLastTime(LocalDateTime lastTime) {
    this.lastTime = lastTime;
  }

  public ChatSortDTO() {
  }

  @Override
  public String toString() {
    return "ChatNoticeVO{"
        + "sort=" + sort
        + ", groupId=" + groupId
        + ", targetId=" + targetId
        + ", divideId=" + divideId
        + ", thumbIconURL='" + thumbIconURL + '\''
        + ", mobile='" + mobile + '\''
        + ", alias='" + alias + '\''
        + ", flagTop=" + flagStar
        + ", flagTop=" + flagTop
        + ", notDisturb=" + notDisturb
        + ", unreadNum=" + unreadNum
        + ", message='" + message + '\''
        + ", messageUser='" + messageUser + '\''
        + ", messageType=" + messageType
        + ", lastTime=" + lastTime
        + '}';
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

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public Long getLastTimestamp() {
    return lastTimestamp;
  }

  public void setLastTimestamp(Long lastTimestamp) {
    this.lastTimestamp = lastTimestamp;
  }
}
