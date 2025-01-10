package com.cp.im.infrastructure.amqp;

import com.cp.im.domain.mq.Message;
import com.cp.im.domain.mq.Packet;
import com.cp.im.domain.repository.ChannelRepository;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
public class SpringRabbitListener {

    @RabbitListener(queues = FanoutConfig.TEST_TAG + FanoutConfig.CHANNEL)
    public void listenQueueTest(Message message) {
        log.warn("listenQueueTest writeAndFlush message: {}", message);
    }

    @RabbitListener(queues = FanoutConfig.CHANNEL_TAG + FanoutConfig.CHANNEL)
    public void listenQueueChannel(Message message) {
        Packet packet = message.getPacket();
        long userId = packet.getUserId();
        String handlerType = message.getHandlerType();
        Channel channel = ChannelRepository.getChannel(userId);
        if (channel != null && channel.isActive()){
            if ("1".equals(handlerType)){
                ChannelRepository.removeChannel(channel);
            }
            channel.writeAndFlush(new Packet(packet.channel, packet.child, packet.bytes));
        }
        log.warn("listenQueueChannel writeAndFlush message: {}", packet);
    }

    @RabbitListener(queues = FanoutConfig.GROUP_CHANNEL_TAG + FanoutConfig.CHANNEL)
    public void listenQueueGroupChannel(Message message) {
        Packet packet = message.getPacket();
        Long groupId = message.getGroupId();
        String handlerType = message.getHandlerType();
        ChannelGroup group = ChannelRepository.getChatGroup(groupId);
        switch (handlerType) {
            case "0":
                group.writeAndFlush(packet);
                break;
            case "1":
                long userId = packet.getUserId();
                Channel channel = ChannelRepository.getChannel(userId);
                if (Objects.nonNull(group)) {
                    group.stream().filter(ch -> !Objects.equals(ch, channel))
                            .forEachOrdered(ch -> ch.writeAndFlush(packet));
                }
                break;
            default:
                break;
        }
        log.warn("listenQueueGroupChannel writeAndFlush message: {}", packet.toString());
    }

}
