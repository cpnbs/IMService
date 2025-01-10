package com.cp.im.infrastructure.mapper;

import com.cp.im.domain.entity.ChatCustomer;
import com.cp.im.infrastructure.annotation.DataSource;
import com.cp.im.infrastructure.db.DataSourceType;
import org.apache.ibatis.annotations.Mapper;

/**
 * 客服数据访问层.
 *
 */

@Mapper
public interface CustomerMapper {

  /**
   * 新增聊天记录.
   *
   * @param message 聊天信息
   */
  @DataSource(value = DataSourceType.CHAT)
  void insertMessage(ChatCustomer message);

}
