package com.cp.im.infrastructure.cmd;

/**
 * 聊天指令.
 *
 * @author wangcaiwen
 * @version v1.0.0
 * @since 2020/4/9 11:10
 */

public class ChitchatCmd {

  /**
   * 进入聊天.
   */
  public static final short ENTER_CHAT_ROOM = 0;

  /**
   * 发送消息.
   */
  public static final short SEND_MESSAGE = 1;

  /**
   * 接收消息.
   */
  public static final short RECEIVE_MESSAGE = 2;

  /**
   * 离开聊天.
   */
  public static final short LEAVE_CHAT_ROOM = 3;

  /**
   * 聆听语音
   */
  public static final short LISTEN_VOICE = 4;

  /**
   * 屏蔽消息
   */
  public static final short SHIELD_MESSAGE = 5;

}
