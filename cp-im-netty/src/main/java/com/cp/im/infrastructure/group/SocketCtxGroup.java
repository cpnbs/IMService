package com.cp.im.infrastructure.group;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.springframework.stereotype.Component;

/**
 * 全局通讯管道.
 *
 * @author wangcaiwen
 * @version v1.0.0
 * @since 2019/10/21 13:49
 */

@Component
public class SocketCtxGroup {

  public static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

}
