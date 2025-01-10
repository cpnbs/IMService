package com.cp.im.application.socket;

import com.cp.im.infrastructure.codec.PacketDecoder;
import com.cp.im.infrastructure.codec.PacketEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import java.util.concurrent.TimeUnit;

/**
 * 管道初始化.
 */

public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {

  @Override
  protected void initChannel(SocketChannel ch) throws Exception {
    // Http解码器
    ch.pipeline()
        .addLast("http-codec", new HttpServerCodec());
    // 块处理器
    ch.pipeline()
        .addLast("http-chunked", new ChunkedWriteHandler());
    // 消息聚合
    ch.pipeline()
        .addLast("aggregator", new HttpObjectAggregator(1024 * 1024 * 1024));
    // websocket
    ch.pipeline()
        .addLast("websocket", new WebSocketServerProtocolHandler("/visit", null, true, 30 * 1024 * 1024));
    // 读空闲超时, 写空闲超时, 读写空闲超时
    ch.pipeline()
        .addLast("ping", new IdleStateHandler(40, 20, 0, TimeUnit.SECONDS));
    // 自定义协议包解码
    ch.pipeline()
        .addLast("dec", new PacketDecoder());
    // 自定义协议包编码
    ch.pipeline()
        .addLast("enc", new PacketEncoder());
    // 自定义handler
    ch.pipeline()
        .addLast("handler", new MyInboundHandler());
  }

}
