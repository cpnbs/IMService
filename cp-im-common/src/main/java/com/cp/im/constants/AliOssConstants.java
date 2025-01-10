package com.cp.im.constants;

import com.cp.im.utils.StringUtils;

/**
 * 阿里OSS常量.
 *
 * @author ☯搬砖道人
 * @version v1.0.0
 * @since 2021/10/13 13:30
 */

public class AliOssConstants {

  /**
   * 阿里OSS标记.
   */
  public static final String ALI_OSS_INDEX = "alioss";

  /**
   * 阿里OSS标记.
   */
  public static final String STORE_INDEX = "store";

  /**
   * 最小头像 w_100.
   */
  public static final String ALI_OSS_RESIZE_ICON_MIN = "?x-oss-process=image/resize,w_50";

  /**
   * 头像 w_100.
   */
  public static final String ALI_OSS_RESIZE_ICON_DEFAULT = "?x-oss-process=image/resize,w_100";

  /**
   * 游戏封面 w_200.
   */
  public static final String ALI_OSS_GAME_COVER_DEFAULT = "?x-oss-process=image/resize,w_200";

  /**
   * 语音房封面 w_300.
   */
  public static final String ALI_OSS_VOICE_ROOM_COVER_DEFAULT = "?x-oss-process=image/resize,w_500";

  /**
   * 语音房礼物 w_150 .
   */
  public static final String ALI_OSS_VOICE_ROOM_GIFT_DEFAULT = "?x-oss-process=image/resize,w_150";

  /**
   * 动态 w_300.
   */
  public static final String ALI_OSS_RESIZE_STORY_MOMENTS_DEFAULT = "?x-oss-process=image/resize,w_500";

  /**
   * 用户头像默认缩放设置.
   *
   * @param iconUrl 用户头像
   * @return 设置结果
   * @author ☯搬砖道人
   * @since 2021/10/13 14:09
   */
  public static String iconMinResizeSetup(String iconUrl) {
    if (StringUtils.isEmpty(iconUrl)) {
      iconUrl = DefaultInfo.DEFAULT_ICON;
    }
    if (iconUrl.contains(ALI_OSS_INDEX)
        || iconUrl.contains(STORE_INDEX)) {
      String[] split = iconUrl.split("\\?");
      // 头像 w_50
      iconUrl = split[0].concat(ALI_OSS_RESIZE_ICON_MIN);
    }
    return iconUrl;
  }

  /**
   * 用户头像默认缩放设置.
   *
   * @param iconUrl 用户头像
   * @return 设置结果
   * @author ☯搬砖道人
   * @since 2021/10/13 14:09
   */
  public static String iconDefaultResize(String iconUrl) {
    if (StringUtils.isEmpty(iconUrl)) {
      iconUrl = DefaultInfo.DEFAULT_ICON;
    }
    if (iconUrl.contains(ALI_OSS_INDEX)
        || iconUrl.contains(STORE_INDEX)) {
      String[] split = iconUrl.split("\\?");
      // 头像 w_100
      iconUrl = split[0].concat(ALI_OSS_RESIZE_ICON_DEFAULT);
    }
    return iconUrl;
  }

  /**
   * 动态图片文件缩放设置.
   *
   * @param fileUrl 文件地址
   * @return java.lang.String
   * @author ☯搬砖道人
   * @since 2021/10/15 14:53
   */
  public static String storyMomentsResizeSetup(String fileUrl) {
    if (StringUtils.isNotEmpty(fileUrl)) {
      String[] split = fileUrl.split("\\?");
      fileUrl = split[0].concat(ALI_OSS_RESIZE_STORY_MOMENTS_DEFAULT);
    }
    return fileUrl;
  }

  /**
   * 游戏封面文件缩放设置.
   *
   * @param fileUrl 文件地址
   * @return java.lang.String
   * @author ☯搬砖道人
   * @since 2021/11/2 13:20
   */
  public static String gameCoverResizeSetup(String fileUrl) {
    if (StringUtils.isNotEmpty(fileUrl)) {
      String[] split = fileUrl.split("\\?");
      fileUrl = split[0].concat(ALI_OSS_GAME_COVER_DEFAULT);
    }
    return fileUrl;
  }

  /**
   * 语音房封面处理.
   *
   * @param fileUrl 文件访问地址
   * @return java.lang.String
   * @author ☯搬砖道人
   * @since 2021/11/1 13:40
   */
  public static String voiceCoverResizeSetup(String fileUrl) {
    if (StringUtils.isNotEmpty(fileUrl)) {
      if (fileUrl.contains(ALI_OSS_INDEX)
          || fileUrl.contains(STORE_INDEX)) {
        String[] split = fileUrl.split("\\?");
        fileUrl = split[0].concat(ALI_OSS_VOICE_ROOM_COVER_DEFAULT);
      }
    }
    return fileUrl;
  }

  /**
   * 语音房礼物处理.
   *
   * @param fileUrl 文件访问地址
   * @return java.lang.String
   * @author ☯搬砖道人
   * @since 2021/11/1 14:52
   */
  public static String voiceGiftResizeSetup(String fileUrl) {
    if (StringUtils.isNotEmpty(fileUrl)) {
      String[] split = fileUrl.split("\\?");
      if (!split[0].endsWith(".svga")) {
        fileUrl = split[0].concat(ALI_OSS_VOICE_ROOM_GIFT_DEFAULT);
      }
    }
    return fileUrl;
  }

}

