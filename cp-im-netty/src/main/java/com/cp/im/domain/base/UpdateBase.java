package com.cp.im.domain.base;

/**
 * 更新基础信息.
 *
 * <p>在采用INNODB的MySQL中，更新操作默认会加行级锁，行级锁是基于索引的，在分析死锁之前需要查询一下mysql的
 * 执行计划，看看是否用到了索引，用到了哪个索引，对于没有用索引的操作会采用表级锁。如果操作用到了主键索引会
 * 先在主键索引上加锁，然后在其他索引上加锁，否则加锁顺序相反。在并发度高的应用中，批量更新一定要带上记录的主键，
 * 优先获取主键上的锁，这样可以减少死锁的发生。
 *
 * @author wangcaiwen
 * @version v1.0.0
 * @since 2021/5/3 0:09
 */

public class UpdateBase {

  /** 主键ID */
  private Long id;

  public UpdateBase() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "UpdateBase{"
        + "id=" + id
        + '}';
  }
}
