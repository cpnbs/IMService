package com.cp.im.domain.mq;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * 数据包.
 *
 * @author wangcaiwen
 * @version v1.0.0
 * @since 2020/3/20 12:44
 */

@Data
@NoArgsConstructor
public class Packet implements Serializable {
  private static final long serialVersionUID = -354303583334661287L;

  /** 消息号. */
  public int channel;

  /** 子消息号. */
  public short child;

  /** 用户ID. */
  public long userId;

  /** 附属ID. */
  public long attachId;

  /** 消息体. */
  public byte[] bytes;

  public Packet(int channel, short child, byte[] bytes) {
    this.channel = channel;
    this.child = child;
    this.bytes = bytes;
  }

  @Override
  public String toString() {
    return "CMD=[" + channel +
        "], CHILD=[" + child +
        "], USER=[" + userId +
        "], ATTACH=[" + attachId + "]";
  }

}
