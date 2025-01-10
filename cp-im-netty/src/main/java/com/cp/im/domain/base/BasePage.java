package com.cp.im.domain.base;

/**
 * 分页信息.
 *
 * @author wangcaiwen
 * @version v1.0.0
 * @since 2021/2/24 2:52
 */

public class BasePage {

  /** 分页页码. */
  private Integer pageNum;
  /** 分页条数. */
  private Integer pageSize;

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

  @Override
  public String toString() {
    return "pageNum=" + pageNum
        + ", pageSize=" + pageSize;
  }

}
