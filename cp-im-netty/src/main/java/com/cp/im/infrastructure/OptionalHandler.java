package com.cp.im.infrastructure;

import com.cp.im.domain.mq.Packet;
import com.cp.im.result.Result;
import io.netty.channel.Channel;
import java.util.Map;

/**
 * 操作处理.
 *
 * @author wangcaiwen
 * @version v2.0.0
 * @since 2020/5/15 14:26
 */

public abstract class OptionalHandler {

  /**
   * 操作处理.
   *
   * @param channel 通信管道
   * @param packet  数据包
   * @author wangcaiwen
   * @since 2020/5/15 21:09
   */
  public abstract void handle(Channel channel, Packet packet);

  /**
   * 处理扩展.
   *
   * @param index  处理索引
   * @param params 处理参数
   * @author xxxxxxxxxx
   * @since 2021/9/7 16:27
   */
  public void handleExtra(short index, Map<String, Object> params) {
    // EMPTY_HANDLER
  }

  /**
   * 请求处理.
   *
   * @param index  请求索引
   * @param params 请求参数
   * @return 请求结果
   * @author xxxxxxxxxx
   * @since 2021/9/7 16:36
   */
  public Result<?> request(short index, Map<String, Object> params) {
    return null;
  }

  /**
   * 异常处理.
   *
   * @param channel 通信管道
   * @param userId  用户ID
   * @author xxxxxxxxxx
   * @since 2021/9/7 13:47
   */
  public void exception(Channel channel, Long userId) {
    // EMPTY_HANDLER
  }

}
