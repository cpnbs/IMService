package com.cp.im.domain.vo;

import com.cp.im.domain.dto.ChatSortDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 信息通知.
 *
 * @author wangcaiwen
 * @version v1.0.0
 * @since 2021/2/23 15:09
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatNoticeVO implements Serializable {
  private static final long serialVersionUID = 7932447614969576689L;

  /**
   * 总条数.
   */
  private Long total;
  /**
   * 分页页码.
   */
  private Integer pageNum;
  /**
   * 分页条数.
   */
  private Integer pageSize;
  /**
   * 总页数.
   */
  private Integer pages;
  /**
   * 未读数量.
   */
  private Integer unRead;
  /**
   * 聊天列表.
   */
  private List<ChatSortDTO> chatList;

  private List<Map<String,Object>> groupList;

  public Long getTotal() {
    return total;
  }

  public void setTotal(Long total) {
    this.total = total;
  }

  public Integer getPageNum() {
    return pageNum;
  }

  public void setPageNum(Integer pageNum) {
    this.pageNum = pageNum;
  }

  public Integer getPageSize() {
    return pageSize;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

  public Integer getPages() {
    return pages;
  }

  public void setPages(Integer pages) {
    this.pages = pages;
  }

  public Integer getUnRead() {
    return unRead;
  }

  public void setUnRead(Integer unRead) {
    this.unRead = unRead;
  }

  public List<ChatSortDTO> getChatList() {
    return chatList;
  }

  public void setChatList(List<ChatSortDTO> chatList) {
    this.chatList = chatList;
  }

  public ChatNoticeVO() {
  }

  public ChatNoticeVO(Integer pageNum, Integer pageSize) {
    this.pageNum = pageNum;
    this.pageSize = pageSize;
    this.total = 0L;
    this.unRead = 0;
    this.chatList = Collections.emptyList();
  }

  @Override
  public String toString() {
    return "ChatNoticeVO{"
        + "total=" + total
        + ", pageNum=" + pageNum
        + ", pageSize=" + pageSize
        + ", pages=" + pages
        + ", unRead=" + unRead
        + ", chatList=" + chatList
        + ", groupList=" + groupList
        + '}';
  }

  public List<Map<String, Object>> getGroupList() {
    return groupList;
  }

  public void setGroupList(List<Map<String, Object>> groupList) {
    this.groupList = groupList;
  }
}
