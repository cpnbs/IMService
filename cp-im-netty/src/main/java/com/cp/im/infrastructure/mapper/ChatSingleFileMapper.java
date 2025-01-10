package com.cp.im.infrastructure.mapper;

import com.cp.im.domain.entity.ChatSingleFile;
import com.cp.im.infrastructure.annotation.DataSource;
import com.cp.im.infrastructure.db.DataSourceType;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChatSingleFileMapper {

  /**
   * 新的文件记录.
   *
   * @param fileInfo 文件信息
   */
  @DataSource(value = DataSourceType.CHAT)
  void insertFile(ChatSingleFile fileInfo);

  /**
   * 获得文件信息.
   *
   * @param recordId 记录ID
   * @return 文件信息
   * @author wangcaiwen
   * @since 2021/2/24 9:35
   */
  @DataSource(value = DataSourceType.CHAT)
  ChatSingleFile getFileInfo(Long recordId);
}
