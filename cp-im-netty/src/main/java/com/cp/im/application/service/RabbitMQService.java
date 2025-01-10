package com.cp.im.application.service;

import com.cp.im.domain.mq.Message;

public interface RabbitMQService {

    void send(Message message);
}
