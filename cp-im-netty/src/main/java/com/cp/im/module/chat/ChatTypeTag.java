package com.cp.im.module.chat;

import com.google.common.collect.Maps;
import java.util.HashMap;

/**
 * 聊天标签.
 *
 * @author xxxxxxxxxx
 * @version v1.0.0
 * @since 2021/9/8 10:10
 */

public class ChatTypeTag {

  /** 单聊. */
  public static final int SINGLE_CHAT = 101;

  /** 群聊. */
  public static final int GROUP_CHAT = 102;

  private static final HashMap<Integer, Integer> CHAT_TAG = Maps.newHashMap();

  static {
    CHAT_TAG.put(1, SINGLE_CHAT);
    CHAT_TAG.put(2, GROUP_CHAT);
  }

  /**
   * 获取聊天分类指令.
   *
   * @param index 聊天索引
   * @return 指令
   */
  public static Integer getChatOrderTagByIndex(Integer index) {
    return CHAT_TAG.getOrDefault(index, 0);
  }

}
