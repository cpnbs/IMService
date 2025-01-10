package com.cp.im.test;

import com.cp.im.infrastructure.utils.FileUtil;
import com.cp.im.proto.c10000msg.C10000;
import com.cp.im.proto.c10002msg.C10002;
import com.google.protobuf.ByteString;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import java.net.URI;
import java.time.LocalDate;

/**
 * @author WangCaiWen Created on 2020/4/3 9:31
 */
public class ClientByNetty {

  public static void main(String[] args) throws Exception {

//    ByteBuf msgBuffer = Unpooled.buffer();
//
//    msgBuffer.writeInt(10000);
//    msgBuffer.writeShort(1);
//    msgBuffer.writeLong(67436);
//    msgBuffer.writeLong(0);
//
//    C10000.C100001c2s.Builder builder = C10000.C100001c2s.newBuilder();
//    builder.setIdentity(1);
//    builder.setDeviceId("3333333333");
//    builder.setToken("1aa528aced05455ab3916d105893e43b");
//    msgBuffer.writeBytes(builder.build().toByteArray());
//
//    Integer p1 = msgBuffer.readInt();
//    Short p2 = msgBuffer.readShort();
//    Long p3 = msgBuffer.readLong();
//    Long p4 = msgBuffer.readLong();
//
//    byte[] bytes = new byte[msgBuffer.readableBytes()];
//    msgBuffer.readBytes(bytes);
//
//    C10000.C100001c2s p5 = C10000.C100001c2s.parseFrom(bytes);

    //netty基本操作，线程组
    EventLoopGroup group = new NioEventLoopGroup();
    //netty基本操作，启动类
    Bootstrap boot = new Bootstrap();
    boot.option(ChannelOption.SO_KEEPALIVE, true)
        .option(ChannelOption.TCP_NODELAY, true)
        .group(group)
        .handler(new LoggingHandler(LogLevel.INFO))
        .channel(NioSocketChannel.class)
        .handler(new ChannelInitializer<SocketChannel>() {
          @Override
          protected void initChannel(SocketChannel socketChannel) throws Exception {
            ChannelPipeline pipeline = socketChannel.pipeline();
            pipeline.addLast("http-codec", new HttpClientCodec());
            pipeline.addLast("aggregator", new HttpObjectAggregator(1024 * 1024 * 10));
            pipeline.addLast("hookedHandler", new WebSocketClientHandler());
          }
        });
    //webSocket连接的地址，/hello是因为在服务端的websocketHandler设置的
//    URI webSocketUrl = new URI("ws://192.168.0.133:9199/online");
//    URI webSocketUrl = new URI("wss://cc.365-farm.com:80/visit");
    URI webSocketUrl = new URI("ws://192.168.2.121:7041/visit");
    HttpHeaders httpHeaders = new DefaultHttpHeaders();
    //进行握手
    WebSocketClientHandshaker handShaker = WebSocketClientHandshakerFactory
        .newHandshaker(webSocketUrl, WebSocketVersion.V13, (String) null, true, httpHeaders);
    //客户端与服务端连接的通道，final修饰表示只会有一个
    final Channel channel = boot.connect(webSocketUrl.getHost(), webSocketUrl.getPort()).sync().channel();
    WebSocketClientHandler handler = (WebSocketClientHandler) channel.pipeline().get("hookedHandler");
    handler.setHandshaker(handShaker);
    handShaker.handshake(channel);
    //阻塞等待是否握手成功
    handler.handshakeFuture().sync();
    System.out.println("握手成功");

    //loginMessage(channel);

    //sendMessage(channel);

    for (int i = 0; i < 10000; i++) {
      sendHB(channel);
      try {
        Thread.sleep(10000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }


  }

  private static void sendHB(Channel channel) {

    ByteBuf msgBuffer = Unpooled.buffer();

    msgBuffer.writeInt(10000);
    msgBuffer.writeShort(0);
    msgBuffer.writeLong(67436);
    msgBuffer.writeLong(0);

    WebSocketFrame frame = new BinaryWebSocketFrame(msgBuffer);
    channel.writeAndFlush(frame);
  }

  private static void loginMessage(Channel channel) {
    ByteBuf msgBuffer = Unpooled.buffer();

    msgBuffer.writeInt(10000);
    msgBuffer.writeShort(1);
    msgBuffer.writeLong(67436);
    msgBuffer.writeLong(0);

    C10000.C100001c2s.Builder builder = C10000.C100001c2s.newBuilder();
    builder.setIdentity(1);
    builder.setDeviceId("3333333333");
    builder.setToken("1aa528aced05455ab3916d105893e43b");
    msgBuffer.writeBytes(builder.build().toByteArray());

    WebSocketFrame frame = new BinaryWebSocketFrame(msgBuffer);
    channel.writeAndFlush(frame);


  }

  private static void sendMessage(Channel channel) {



    ByteBuf msgBuffer = Unpooled.buffer();

    msgBuffer.writeInt(10002);
    msgBuffer.writeShort(1);
    msgBuffer.writeLong(67436);
    msgBuffer.writeLong(0);

    C10002.C100021c2s.Builder builder = C10002.C100021c2s.newBuilder();
    builder.setSort(1);
    builder.setTargetId(67431);
    builder.setMessageType(0);
    builder.setMessage("w");
    builder.setMessageId(System.currentTimeMillis() + "" + 67436);

//    C10002.ESImageInfo.Builder imageInfo = C10002.ESImageInfo.newBuilder();
//    imageInfo.setWidth(600);
//    imageInfo.setHeight(800);
//    imageInfo.setImageExt("jpg");
//    imageInfo.setImageFile(ByteString.copyFrom(FileUtil.ImageToBytes("C:\\Users\\mi\\Pictures\\Saved Pictures\\001.jpg")));
//    builder.setImageInfo(imageInfo);

    msgBuffer.writeBytes(builder.build().toByteArray());

    WebSocketFrame frame = new BinaryWebSocketFrame(msgBuffer);
    channel.writeAndFlush(frame);



    // ====================================================================================================
//    F20000.F200001C2S.Builder builder = F20000.F200001C2S.newBuilder();
//    builder.setLevel(1);
//    builder.setSex(2);
//    builder.setNickName("阿松");
//    builder.setThumbIconURL("http://qzapp.qlogo.cn/qzapp/1110572572/1132F214F6AB33D77998BF1CCA979535/100");
    // ====================================================================================================
//    F20001.F200012C2S.Builder match = F20001.F200012C2S.newBuilder();
//    match.setGameCode(30021L);
//    match.setNumberMatch(1);
    // ====================================================================================================
//    ByteBuf msgBuffer = Unpooled.buffer();
//    msgBuffer.writeInt(30051);
//    msgBuffer.writeShort(1);
//    msgBuffer.writeInt(11);
//    msgBuffer.writeInt(2020);
//
//    F30051.F300511C2S.Builder sss = F30051.F300511C2S.newBuilder();
//    sss.setIsReady(1);
//    System.out.println(sss.build().toByteArray().length);
//    msgBuffer.writeBytes(sss.build().toByteArray());

//    ByteBuf msgBuffer = Unpooled.buffer();


//    ByteBuf msgBuffer = Unpooled.buffer();
//
//    msgBuffer.writeInt(10000);
//    msgBuffer.writeShort(0);
//    msgBuffer.writeLong(67431);
//    msgBuffer.writeLong(0);
//
//      WebSocketFrame frame = new BinaryWebSocketFrame(msgBuffer);
//      channel.writeAndFlush(frame);



//    System.out.println("登录服务");
//    // 休眠5s
//
//    ByteBuf msg = Unpooled.buffer();
//    msg.writeInt(20001);
//    msg.writeShort(2);
//    msg.writeLong(12345679L);
//    msg.writeLong(0L);
//    msg.writeBytes(match.build().toByteArray());
//    WebSocketFrame matchFrame = new BinaryWebSocketFrame(msg);
//    channel.writeAndFlush(matchFrame);
//    System.out.println("匹配飞行棋");
  }

}
