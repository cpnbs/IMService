package com.cp.im.application.socket;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.NacosServiceManager;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.cp.im.utils.ExceptionUtil;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.util.Properties;

/**
 * websocket服务.
 *
 * @author wangcaiwen
 * @version v1.0.0
 * @since 2020/4/1 9:09
 */

@Slf4j
@Component
public class NettySocketServer {

  @Value("${netty.port}")
  private Integer port;

  @Value("${netty.application.name}")
  private String serverName;

  @Autowired
  private NacosDiscoveryProperties nacosDiscoveryProperties;

  private EventLoopGroup bossGroup;

  private EventLoopGroup workGroup;

  /**
   * 开启socket服务.
   *
   * @author wangcaiwen
   * @since 2020/4/1 9:09
   */
  public void startServer() {
    registerNamingService(serverName, port);
    try {
      bossGroup = new NioEventLoopGroup();
      workGroup = new NioEventLoopGroup();
      ServerBootstrap serverBootstrap = new ServerBootstrap();
      serverBootstrap.group(bossGroup, workGroup)
          .channel(NioServerSocketChannel.class)
          .handler(new LoggingHandler(LogLevel.INFO))
          .childOption(ChannelOption.SO_KEEPALIVE, true)
          .option(ChannelOption.SO_BACKLOG, 1024 * 1024 * 10)
          .childHandler(new MyChannelInitializer());
      ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
      channelFuture.channel().closeFuture().sync();
    } catch (Exception e) {
      log.error(e.getMessage());
      log.error(ExceptionUtil.getStackTrace(e));
    } finally {
      bossGroup.shutdownGracefully();
      workGroup.shutdownGracefully();
    }
  }

  /**
   * 注册到 nacos 服务中
   *
   * @param nettyName netty服务名称
   * @param nettyPort netty服务端口
   */
  private void registerNamingService(String nettyName, Integer nettyPort) {
    try {
      NamingService namingService = NamingFactory.createNamingService(nacosDiscoveryProperties.getNacosProperties());
      namingService.registerInstance(nettyName, nacosDiscoveryProperties.getGroup(), nacosDiscoveryProperties.getIp(), nettyPort);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
