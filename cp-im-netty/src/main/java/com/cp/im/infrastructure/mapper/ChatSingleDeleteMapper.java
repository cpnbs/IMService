package com.cp.im.infrastructure.mapper;

import com.cp.im.interfaces.dto.DeleteChatMsgDTO;
import com.cp.im.domain.entity.ChatSingle;
import com.cp.im.domain.entity.ChatSingleDelete;
import com.cp.im.infrastructure.annotation.DataSource;
import com.cp.im.infrastructure.db.DataSourceType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

/**
 * 单聊删除信息访问层.
 */

@Mapper
public interface ChatSingleDeleteMapper {

  /**
   * 批量新增标记数据.
   *
   * @param list 新增信息
   * @author wangcaiwen
   * @since 2021/4/3 17:15
   */
  @DataSource(value = DataSourceType.CHAT)
  void insertMessage(@Param("list") List<Map<String, Object>> list);

  /**
   * 批量新增标记数据.
   *
   * @param list 新增信息
   * @author wangcaiwen
   * @since 2021/4/3 17:15
   */
  @DataSource(value = DataSourceType.CHAT)
  void batchInsertWhisperDeleteFlag(@Param("list") List<ChatSingleDelete> list);

  /**
   * 新增标记数据.
   *
   * @param insertInfo 新增信息
   * @author wangcaiwen
   * @since 2021/4/3 17:15
   */
  @DataSource(value = DataSourceType.CHAT)
  void insertChatMessageRelation(ChatSingleDelete insertInfo);

  /**
   * 删除关联所有数据.
   *
   * @param userId 用户ID
   * @author wangcaiwen
   * @since 2021/4/3 17:14
   */
  @DataSource(value = DataSourceType.CHAT)
  void deleteMessageAll(Long userId);
  
  /**
   * 更新删除标记.
   *
   * @param deleteInfo 删除信息
   * @author wangcaiwen
   * @since 2021/2/24 14:13
   */
  @DataSource(value = DataSourceType.CHAT)
  void updateDeleteFlag(@Param("userId") Long userId, @Param("targetId") Long targetId);

  /**
   * 是否存在.
   *
   * @param userId 用户ID
   * @param targetId 目标ID
   * @return 查询结果
   * @author wangcaiwen
   * @since 2021/4/3 17:17
   */
  @DataSource(value = DataSourceType.CHAT)
  Integer isExists(@Param("userId") Long userId, @Param("targetId") Long targetId);

  /**
   * 标记时间.
   *
   * @param userId 用户ID
   * @param targetId 目标ID
   * @return 标记时间
   * @author wangcaiwen
   * @since 2021/3/31 16:59
   */
  @DataSource(value = DataSourceType.CHAT)
  String queryMessageTime(@Param("userId") Long userId, @Param("targetId") Long targetId);

}
