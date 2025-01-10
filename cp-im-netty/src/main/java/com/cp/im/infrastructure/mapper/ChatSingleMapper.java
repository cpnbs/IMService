package com.cp.im.infrastructure.mapper;

import com.cp.im.domain.base.ChatBase;
import com.cp.im.domain.base.UpdateBase;
import com.cp.im.domain.dto.ChatSimpleInfoDTO;
import com.cp.im.domain.dto.ChatSortDTO;
import com.cp.im.domain.dto.UpdateChatStatusDTO;
import com.cp.im.domain.dto.UpdateShowDTO;
import com.cp.im.domain.entity.ChatCustomer;
import com.cp.im.domain.entity.ChatSingle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cp.im.domain.entity.TokenInfo;
import com.cp.im.domain.vo.SingleChatSettingVO;
import com.cp.im.infrastructure.annotation.DataSource;
import com.cp.im.infrastructure.db.DataSourceType;
import com.cp.im.interfaces.dto.DeleteChatMsgDTO;
import com.cp.im.interfaces.dto.QueryChatDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 单聊数据访问层.
 */

@Mapper
public interface ChatSingleMapper {

  /**
   * 更新聊天状态.
   *
   * @param updateInfo 更新信息
   * @author wangcaiwen
   * @since 2021/3/30 17:57
   */
  @DataSource(value = DataSourceType.CHAT)
  void updateStatus(UpdateChatStatusDTO updateInfo);

  /**
   * 更新简略信息.
   *
   * @param updateInfo 更新信息
   * @author wangcaiwen
   * @since 2021/3/30 20:29
   */
  @DataSource(value = DataSourceType.CHAT)
  void updateBriefInfo(ChatSingle updateInfo);

  @DataSource(value = DataSourceType.CHAT)
  Long getPrimaryKeyId(@Param("userId") Long userId, @Param("targetId") Long targetId);

  @DataSource(value = DataSourceType.CHAT)
  Integer getUnreadNumber(Long id);

  /**
   * 获得聊天信息.
   *
   * @param userId 用户ID
   * @param targetId 目标ID
   */
  @DataSource(value = DataSourceType.CHAT)
  ChatSimpleInfoDTO getChatInfo(@Param("userId") Long userId, @Param("targetId") Long targetId);

  /**
   * 新增聊天关系.
   *
   * @param list 关系列表
   */
  @DataSource(value = DataSourceType.CHAT)
  void newUserChatLink(@Param("list") List<ChatSingle> list);

  /**
   * 批量新增聊天关系.
   *
   * @param list 聊天列表
   */
  @DataSource(value = DataSourceType.CHAT)
  void batchInsertWhisperChat(@Param("list") List<ChatSingle> list);

  /**
   * 更新聊天设置.
   *
   * @param chat 聊天信息
   */
  @DataSource(value = DataSourceType.CHAT)
  void updateChatSetting(ChatSingle chat);

  /**
   * 更新聊天展示.
   *
   * @param updateInfo 更新信息
   */
  @DataSource(value = DataSourceType.CHAT)
  void updateChatShow(UpdateShowDTO updateInfo);

  /**
   * 更新设定.
   *
   * @param params 添加消息
   */
  @DataSource(value = DataSourceType.CHAT)
  void updateUserChatSetting(Map<String, Object> params);

  /**
   * 更新聊天状态.
   *
   * @param userId 用户ID
   */
  @DataSource(value = DataSourceType.CHAT)
  void updateUserChatStatusAll(Long userId);

  /**
   * 获得聊天设置.
   *
   * @param queryInfo 查询信息
   * @return 设置信息
   */
  @DataSource(value = DataSourceType.CHAT)
  SingleChatSettingVO getChatSetting(QueryChatDTO queryInfo);

  /**
   * 聊天信息列表.
   *
   * @param userId 用户ID
   * @param pastTime 时间
   * @param filterIdS 筛选id
   * @return 信息列表
   */
  @DataSource(value = DataSourceType.CHAT)
  List<ChatSortDTO> selectChatInfoList(@Param("userId") Long userId, @Param("pastTime") String pastTime, @Param("filterIds") List<Long> filterIdS);

  @DataSource(value = DataSourceType.CHAT)
  List<ChatSortDTO> selectChatUserList(@Param("userId") Long userId, @Param("filterIds") List<Long> filterIdS);


  /**
   * 解除所有联系.
   *
   * @param userId 用户ID
   *
   */
  @DataSource(value = DataSourceType.CHAT)
  void relieveChatRelation(Long userId);

  /**
   * 检查关系.
   *
   * @param userId 用户ID
   * @param targetId 目标ID
   * @return 检查结果
   */
  @DataSource(value = DataSourceType.CHAT)
  Integer checkUserRelation(@Param("userId") Long userId, @Param("targetId") Long targetId);

  /**
   * 查询状态.
   *
   * @param queryInfo 查询信息
   * @return 状态信息
   */
  @DataSource(value = DataSourceType.CHAT)
  Integer queryStatus(ChatBase queryInfo);

  /**
   * 更新消息ID.
   *
   * @param deleteInfo 删除信息
   */
  @DataSource(value = DataSourceType.CHAT)
  void updateMessageId(DeleteChatMsgDTO deleteInfo);


  /**
   * 验证联系.
   *
   * @param queryInfo 查询信息
   * @return 查询结果
   */
  @DataSource(value = DataSourceType.CHAT)
  Integer userRelationIsExist(ChatBase queryInfo);

  /**
   * 查询关系是否存在.
   *
   * @param queryInfo 查询信息
   * @return 查询结果
   */
  @DataSource(value = DataSourceType.CHAT)
  Integer queryRelationIsExist(ChatBase queryInfo);

  /**
   * 获得未读数量.
   *
   * @param userId 用户ID
   * @return java.lang.Integer
   */
  @DataSource(value = DataSourceType.CHAT)
  Integer getUserUnreadNum(Long userId);

  /**
   * 获得更新基础信息.
   *
   * @param userId 用户ID
   * @param targetId 目标ID
   * @return 基础信息
   */
  @DataSource(value = DataSourceType.CHAT)
  UpdateBase getUpdateBase(@Param("userId") Long userId, @Param("targetId") Long targetId);

  @DataSource(value = DataSourceType.CHAT)
  Map<String, Object> getUserBase(@Param("userId") Long userId, @Param("targetId") Long targetId);

  @DataSource(value = DataSourceType.CHAT)
  Map<String, Object> getUserBase2(@Param("userId") Long userId, @Param("targetId") Long targetId);

  @DataSource(value = DataSourceType.CHAT)
  List<Map<String, Object>> getUserBaseList(@Param("list") List<Long> userIdList);

  @DataSource(value = DataSourceType.CHAT)
  Map<String, Object> getUser(@Param("mobile") String mobile);

  @DataSource(value = DataSourceType.CHAT)
  String getToken(@Param("userId") Long userId);

  @DataSource(value = DataSourceType.CHAT)
  void insertToken(TokenInfo info);

  @DataSource(value = DataSourceType.CHAT)
  ArrayList<Long> getTargetList(@Param("userId") Long userId);

  /**
   * 筛选聊天列表
   *
   * @param userId 用户id
   * @param filterName 筛选名
   * @return
   */
  @DataSource(value = DataSourceType.CHAT)
  ArrayList<Long> filterChatList(@Param("userId") Long userId, @Param("filterName") String filterName);
}
