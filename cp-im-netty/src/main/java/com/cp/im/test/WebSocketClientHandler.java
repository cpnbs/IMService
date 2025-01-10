package com.cp.im.test;

import com.cp.im.domain.mq.Packet;
import com.cp.im.proto.c10001msg.C10001;
import com.cp.im.proto.c10002msg.C10002;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketHandshakeException;
import io.netty.util.CharsetUtil;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @author WangCaiWen Created on 2020/4/3 9:33
 */

public class WebSocketClientHandler extends SimpleChannelInboundHandler<Object> {

  /**
   * 握手的状态信息
   */
  WebSocketClientHandshaker handshaker;
  /**
   * netty自带的异步处理.
   */
  ChannelPromise handshakeFuture;

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
    System.out.println("当前握手的状态" + this.handshaker.isHandshakeComplete());
    Channel ch = ctx.channel();
    FullHttpResponse response;
    //进行握手操作
    if (!this.handshaker.isHandshakeComplete()) {
      try {
        response = (FullHttpResponse) msg;
        //握手协议返回，设置结束握手
        this.handshaker.finishHandshake(ch, response);
        //设置成功
        this.handshakeFuture.setSuccess();
        System.out.println("服务端的消息" + response.headers());
      } catch (WebSocketHandshakeException var7) {
        FullHttpResponse res = (FullHttpResponse) msg;
        String errorMsg = String.format("握手失败,status:%s,reason:%s", res.status(),
            res.content().toString(CharsetUtil.UTF_8));
        this.handshakeFuture.setFailure(new Exception(errorMsg));
      }
    } else if (msg instanceof FullHttpResponse) {
      response = (FullHttpResponse) msg;
      throw new IllegalStateException(
          "Unexpected FullHttpResponse (getStatus=" + response.status() + ", content=" + response
              .content().toString(CharsetUtil.UTF_8) + ')');
    } else {
      //接收服务端的消息
      WebSocketFrame frame = (WebSocketFrame) msg;
      //文本信息
      if (frame instanceof TextWebSocketFrame) {
        TextWebSocketFrame textFrame = (TextWebSocketFrame) frame;
        System.out.println("客户端接收的消息是:" + textFrame.text());
      }
      //二进制信息
      if (frame instanceof BinaryWebSocketFrame) {
        BinaryWebSocketFrame binFrame = (BinaryWebSocketFrame) frame;
        System.out.println("BinaryWebSocketFrame");
        ByteBuf buf = binFrame.content();
        buf.markReaderIndex();
        int channel = buf.readInt();
        short child = buf.readShort();
//        long userId = buf.readLong();
//        long attachId = buf.readLong();
        byte[] bytes = new byte[buf.readableBytes()];
        buf.readBytes(bytes);

        if (channel == 10002 && child == 1){

          C10002.C100021s2c builder = C10002.C100021s2c.parseFrom(bytes);
          System.out.println(builder.getRecordId());
          System.out.println(builder.getMessageId());
        }

//        ByteBuf msgBuffer = Unpooled.buffer();
//        msgBuffer.writeInt(10000);
//        msgBuffer.writeShort(0);
//        msgBuffer.writeInt(189869502);
//        msgBuffer.writeInt(30291);
//        WebSocketFrame frames = new BinaryWebSocketFrame(msgBuffer);
//           ctx.channel().writeAndFlush(frames);
//
//                short child = buf.readShort();
//                byte[] bytes = new byte[buf.readableBytes()];
//                buf.readBytes(bytes);
//
//                F10001.F10001C2S request = F10001.F10001C2S.parseFrom(bytes);
//
//                F30001.F30001S2C builder = F30001.F30001S2C.parseFrom(bytes);
//
//                System.out.println(request.getMessage());

      }
      //ping信息
      if (frame instanceof PongWebSocketFrame) {
        System.out.println("WebSocket Client received pong");
      }
      //关闭消息
      if (frame instanceof CloseWebSocketFrame) {
        System.out.println("receive close frame");
        ch.close();
      }

    }
  }

  private static LocalDateTime udt = null;

  /**
   * Handler活跃状态，表示连接成功
   */
  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    udt = LocalDateTime.now();
    System.out.println("与服务端连接成功");
  }

  /**
   * 非活跃状态，没有连接远程主机的时候。
   */
  @Override
  public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    LocalDateTime nds = LocalDateTime.now();
    Duration duration = Duration.between(udt, nds);
    int second = Math.toIntExact(duration.getSeconds());
    System.out.println("主机关闭 " + second);
  }

  /**
   * 异常处理
   */
  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    System.out.println("连接异常：" + cause.getMessage());
    ctx.close();
  }

  @Override
  public void handlerAdded(ChannelHandlerContext ctx) {
    this.handshakeFuture = ctx.newPromise();
  }

  public WebSocketClientHandshaker getHandshaker() {
    return handshaker;
  }

  public void setHandshaker(WebSocketClientHandshaker handshaker) {
    this.handshaker = handshaker;
  }

  public ChannelPromise getHandshakeFuture() {
    return handshakeFuture;
  }

  public void setHandshakeFuture(ChannelPromise handshakeFuture) {
    this.handshakeFuture = handshakeFuture;
  }

  public ChannelFuture handshakeFuture() {
    return this.handshakeFuture;
  }
}
