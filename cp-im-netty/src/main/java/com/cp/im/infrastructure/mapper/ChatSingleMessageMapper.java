package com.cp.im.infrastructure.mapper;

import com.cp.im.domain.entity.ChatSingleMessage;
import com.cp.im.domain.dto.SingleChatMsgDTO;
import java.util.List;
import java.util.Map;

import com.cp.im.infrastructure.annotation.DataSource;
import com.cp.im.infrastructure.db.DataSourceType;
import com.cp.im.interfaces.dto.QueryChatDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 单聊数据访问层.
 *
 */

@Mapper
public interface ChatSingleMessageMapper {

  /**
   * 新增聊天记录.
   *
   * @param chatSingleMessage 聊天信息
   */
  @DataSource(value = DataSourceType.CHAT)
  void insertSingleMessage(ChatSingleMessage chatSingleMessage);

  /**
   * 删除消息.
   *
   * @param recordId 记录ID
   */
  @DataSource(value = DataSourceType.CHAT)
  void deleteMessage(Long recordId);

  /**
   * 更新消息操作.
   *
   * @param recordId 记录ID
   */
  @DataSource(value = DataSourceType.CHAT)
  void updateMessageAction(Long recordId);

  /**
   * 更新消息操作.
   *
   * @param recordId 记录ID
   */
  @DataSource(value = DataSourceType.CHAT)
  void updateMessageShield(Long recordId);

  @DataSource(value = DataSourceType.CHAT)
  Map<String, Object> selectMessageByRecordId(Long recordId);

  /**
   * 聊天记录.
   *
   * @param queryInfo 查询信息
   * @return 查询结果
   */
  @DataSource(value = DataSourceType.CHAT)
  List<SingleChatMsgDTO> getChatMessageList(QueryChatDTO queryInfo);

}
