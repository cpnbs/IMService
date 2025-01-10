package com.cp.im.infrastructure.mq;

import com.cp.im.domain.mq.Message;
import com.cp.im.domain.mq.Packet;
import com.cp.im.domain.repository.ChannelRepository;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MqAuto {

    public static void onMessage(Message message) {
        Packet packet = message.getPacket();
        long userId = packet.getUserId();
        String handlerType = message.getHandlerType();
        Channel channel = ChannelRepository.getChannel(userId);
        if (channel != null && channel.isActive()){
            if ("1".equals(handlerType)){
                ChannelRepository.removeChannel(channel);
            }
            channel.writeAndFlush(new Packet(packet.channel, packet.child, packet.bytes));
            log.warn("channel writeAndFlush message: {}", packet);
        }
    }
}
