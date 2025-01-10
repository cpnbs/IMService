package com.cp.im.infrastructure.codec;

import com.cp.im.domain.mq.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 解码器.
 */

@Component
public class PacketDecoder extends MessageToMessageDecoder<WebSocketFrame> {

  private static final Logger logger = LoggerFactory.getLogger(PacketDecoder.class);

  /** 阅读字节长度. */
  private static final int READABLE_BYTES = 14;
  /** 阅读字节长度.后台. */
  private static final int READABLE_BYTES_REAR = 14;
  /** 阅读字节长度.软件. */
  private static final int READABLE_BYTES_SOFT = 22;
  /** 最小消息号. */
  private static final int MIN_CHANNEL = 10000;
  /** 最大消息号. */
  private static final int MAX_CHANNEL = 99999;
  /** 软件编码. */
  private static final int REAR_CODE = 90000;

  @Override
  protected void decode(ChannelHandlerContext ctx, WebSocketFrame msg, List<Object> out) throws Exception {
    ByteBuf buf = msg.content();
    // 读取标记
    buf.markReaderIndex();
    // 获取包头长度
    int baseLength = buf.readableBytes();
    if (baseLength >= READABLE_BYTES) {
      int channel = buf.readInt();
      if (channel >= MIN_CHANNEL && channel < MAX_CHANNEL) {
        short child = buf.readShort();
        long userId;
        long attachId;
        if (channel < REAR_CODE) {
          userId = buf.readLong();
          attachId = buf.readLong();
          int residue = buf.readableBytes();
          if (baseLength - residue > READABLE_BYTES_SOFT || baseLength - residue < READABLE_BYTES_SOFT) {
            ctx.flush();
            ctx.close();
            ctx.disconnect();
            buf.clear();
            return;
          }
        } else {
          int userNo = buf.readInt();
          int attachNo = buf.readInt();
          userId = userNo;
          attachId = attachNo;
          int residue = buf.readableBytes();
          if (baseLength - residue > READABLE_BYTES_REAR || baseLength - residue < READABLE_BYTES_REAR) {
            ctx.flush();
            ctx.close();
            ctx.disconnect();
            buf.clear();
            return;
          }
        }
        byte[] bytes = new byte[buf.readableBytes()];
        buf.readBytes(bytes);
        Packet packet = new Packet(channel, child, bytes);
        packet.userId = userId;
        packet.attachId = attachId;
        out.add(packet);
        decode(ctx, msg, out);
      } else {
        logger.error("[ERROR]头部信息错误");
        ctx.flush();
        ctx.close();
        ctx.disconnect();
        buf.clear();
      }
    } else {
      buf.clear();
    }
  }

}
