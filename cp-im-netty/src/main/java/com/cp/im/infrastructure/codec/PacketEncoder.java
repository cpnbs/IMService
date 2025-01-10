package com.cp.im.infrastructure.codec;

import com.cp.im.domain.mq.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * 编码器.
 */

@Component
public class PacketEncoder extends MessageToMessageEncoder<Packet> {

  @Override
  protected void encode(ChannelHandlerContext ctx, Packet packet, List<Object> out) throws Exception {
    ByteBuf msgBuffer = Unpooled.buffer();
    msgBuffer.writeInt(packet.channel);
    msgBuffer.writeShort(packet.child);
    if (packet.bytes != null) {
      msgBuffer.writeBytes(packet.bytes);
    }
    WebSocketFrame frame = new BinaryWebSocketFrame(msgBuffer);
    out.add(frame);
  }

}
