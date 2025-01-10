package com.cp.im.domain.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.pagehelper.PageInfo;
import java.io.Serializable;
import java.util.List;

/**
 * <p>分页信息.</p>
 *
 * @author wangcaiwen
 * @version v1.0.0
 * @since 2021/2/24 5:01
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BasePageInfo<T> implements Serializable {
  private static final long serialVersionUID = -7949310011095831613L;

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
   * 列表数据.
   */
  private List<T> list;

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

  public List<T> getList() {
    return list;
  }

  public void setList(List<T> list) {
    this.list = list;
  }

  @Override
  public String toString() {
    return "BasePageInfo{"
        + "total=" + total
        + ", pageNum=" + pageNum
        + ", pageSize=" + pageSize
        + ", pages=" + pages
        + ", list=" + list
        + '}';
  }

  public void setBaseInfoByPageInfo(PageInfo<T> pageInfo) {
    this.total = pageInfo.getTotal();
    this.pageNum = pageInfo.getPageNum();
    this.pageSize = pageInfo.getPageSize();
    this.pages = pageInfo.getPages();
    this.list = pageInfo.getList();
  }
}
