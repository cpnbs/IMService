package com.cp.im.infrastructure.mapper;

import com.cp.im.domain.entity.ChatQa;
import com.cp.im.domain.entity.TokenInfo;
import com.cp.im.domain.vo.ChatDivideVO;
import com.cp.im.domain.vo.ChatEmoticonVO;
import java.util.List;

import com.cp.im.domain.vo.ChatQaVO;
import com.cp.im.infrastructure.annotation.DataSource;
import com.cp.im.infrastructure.annotation.MultilingualCol;
import com.cp.im.infrastructure.db.DataSourceType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 聊天表情数据访问.
 */

@Mapper
public interface ChatEmoticonMapper {

  /**
   * 获得表情.
   *
   * @param categoryId 分类ID
   * @return 表情列表
   */
  @DataSource(value = DataSourceType.CHAT)
  @MultilingualCol(colNames = {"em_name"},aliasNames = {"emName"})
  List<ChatEmoticonVO> selectEmoticon(@Param("categoryId") Integer categoryId);

  @DataSource(value = DataSourceType.CHAT)
  List<ChatDivideVO> selectDivide();

  @DataSource(value = DataSourceType.CHAT)
  List<ChatQaVO> selectQa(@Param("qa") String qa, @Param("userId") Long userId);

  @DataSource(value = DataSourceType.CHAT)
  void insertQa(@Param("qa") String qa, @Param("userId") Long userId);

  @DataSource(value = DataSourceType.CHAT)
  void deleteQa(Long id);

  @DataSource(value = DataSourceType.CHAT)
  void updateQa(Long id, String qa);
}