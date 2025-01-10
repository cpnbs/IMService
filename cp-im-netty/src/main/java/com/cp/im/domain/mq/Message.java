package com.cp.im.domain.mq;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @Author wangxuewen
 * @Date 2021/6/15
 * @Version V3.0
 **/

@Data
public class Message implements Serializable {
    private static final long serialVersionUID = -7081660177831617874L;

    /**
     *
     * 消息主题
     */
    private String topic = "cp-im-netty-topic";

    /**
     * 消息标签
     */
    private String tags;

    /**
     * 处理方式 0-所有channel 1-除自己外channel
     */
    private String handlerType;


    /**
     * 群组id（group_channel传值）
     */
    private Long groupId;

    /**
     * 消息内容
     */
    private Packet packet;
}
