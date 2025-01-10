package com.cp.im.domain.service;

import com.cp.im.application.service.RabbitMQService;
import com.cp.im.domain.mq.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitMQServiceImpl implements RabbitMQService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void send(Message message) {
        rabbitTemplate.convertAndSend(message.getTags(), "", message);
    }
}
