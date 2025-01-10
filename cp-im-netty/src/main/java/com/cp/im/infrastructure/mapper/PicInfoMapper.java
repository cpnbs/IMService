package com.cp.im.infrastructure.mapper;

import com.cp.im.domain.entity.PicInfo;
import com.cp.im.infrastructure.annotation.DataSource;
import com.cp.im.infrastructure.db.DataSourceType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface PicInfoMapper {
    @DataSource(value = DataSourceType.CHAT)
    int deleteByPrimaryKey(String picNewName);

    @DataSource(value = DataSourceType.CHAT)
    int deleteById(@Param("id") Long id);

    @DataSource(value = DataSourceType.CHAT)
    int insert(PicInfo record);

    @DataSource(value = DataSourceType.CHAT)
    PicInfo selectByPrimaryKey(Long id);

    @DataSource(value = DataSourceType.CHAT)
    Map<String, Object> selectPicUrl(@Param("picNewName") String picNewName);

    @DataSource(value = DataSourceType.CHAT)
    int updateByPrimaryKeySelective(PicInfo record);

    @DataSource(value = DataSourceType.CHAT)
    int updateIsViolation(PicInfo record);

    @DataSource(value = DataSourceType.CHAT)
    Map<String, Object> queryPostResource(@Param("url") String url);

    @DataSource(value = DataSourceType.CHAT)
    Map<String, Object> queryVoiceRoomCoverUrl(@Param("url") String url);

    @DataSource(value = DataSourceType.CHAT)
    Map<String, Object> queryBackgroundUrl(@Param("url") String url);

    @DataSource(value = DataSourceType.CHAT)
    Map<String, Object> queryVoiceRoomBackgroundUrl(@Param("url") String url);

    @DataSource(value = DataSourceType.CHAT)
    Map<String, Object> queryUrlUserHeader(@Param("iconUrl") String iconUrl);

    /**
     * 查询单聊图片信息.
     *
     * @param url 目标图片
     * @return 图片信息
     */
    @DataSource(value = DataSourceType.CHAT)
    Map<String, Object> queryChatSingleImgInfo(@Param("url") String url);

    /**
     * 查询群聊图片信息.
     *
     * @param url 目标图片
     * @return 图片信息
     */
    @DataSource(value = DataSourceType.CHAT)
    Map<String, Object> queryChatGroupImgInfo(@Param("url") String url);

    /**
     * 查询公会聊天图片信息.
     *
     * @param url 目标图片
     * @return 图片信息
     */
    @DataSource(value = DataSourceType.CHAT)
    Map<String, Object> queryGuildChatImgInfo(@Param("url") String url);

    @DataSource(value = DataSourceType.CHAT)
    List<Map<String, Object>> fileUrls(@Param("fileName") String fileName);

    @DataSource(value = DataSourceType.CHAT)
    int updatePostResourceUrl(@Param("url") String url, @Param("coverUrl") String coverUrl, @Param("thumbUrl") String thumbUrl, @Param("id") String id);

    @DataSource(value = DataSourceType.CHAT)
    int updateRoomUrl(@Param("coverUrl") String coverUrl, @Param("backgroundUrl") String backgroundUrl, @Param("id") Integer id);

    @DataSource(value = DataSourceType.CHAT)
    int updateUserHeader(@Param("iconUrl") String iconUrl, @Param("thumbIconUrl") String thumbIconUrl, @Param("backgroundUrl") String backgroundUrl, @Param("id") Integer id);

    /**
     * 更新单聊图片信息.
     *
     * @param id 文件ID
     * @param fileUrl 文件访问地址
     * @param coverUrl 文件封面地址
     */
    @DataSource(value = DataSourceType.CHAT)
    void updateChatSingleImgInfo(@Param("id") Long id, @Param("fileUrl") String fileUrl, @Param("coverUrl") String coverUrl);

    /**
     * 更新群聊图片信息.
     *
     * @param id 文件ID
     * @param fileUrl 文件访问地址
     * @param coverUrl 文件封面地址
     */
    @DataSource(value = DataSourceType.CHAT)
    void updateChatGroupImgInfo(@Param("id") Long id, @Param("fileUrl") String fileUrl, @Param("coverUrl") String coverUrl);

    /**
     * 更新公会聊天图片信息.
     *
     * @param id 文件ID
     * @param fileUrl 文件访问地址
     * @param coverUrl 文件封面地址
     */
    @DataSource(value = DataSourceType.CHAT)
    void updateGuildChatImgInfo(@Param("id") Long id, @Param("fileUrl") String fileUrl, @Param("coverUrl") String coverUrl);
}