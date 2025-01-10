package com.cp.im.infrastructure.amqp;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutConfig {

    public static final String CHANNEL = ".geel";

    /**
     * 测试消息
     */
    public static final String TEST_TAG = "fanout.test";

    /**
     * 单发消息
     */
    public static final String CHANNEL_TAG = "fanout.channel";

    /**
     * 群发
     */
    public static final String GROUP_CHANNEL_TAG = "fanout.group.channel";


    /**
     * 声明交换机
     */
    @Bean
    public FanoutExchange exchangeTest(){
        return new FanoutExchange(TEST_TAG);
    }

    /**
     * 声明队列
     */
    @Bean
    public Queue queueTest(){
        return new Queue(TEST_TAG + CHANNEL);
    }

    /**
     * 绑定队列和交换机
     */
    @Bean
    public Binding bindingTest(Queue queueTest, FanoutExchange exchangeTest){
        return BindingBuilder.bind(queueTest).to(exchangeTest);
    }

    //-----------------------------------CHANNEL_TAG----------------------------------------------------------------
    /**
     * 声明交换机
     */
    @Bean
    public FanoutExchange exchangeChannel(){
        return new FanoutExchange(CHANNEL_TAG);
    }

    /**
     * 声明队列
     */
    @Bean
    public Queue queueChannel(){
        return new Queue(CHANNEL_TAG + CHANNEL);
    }

    /**
     * 绑定队列和交换机
     */
    @Bean
    public Binding bindingChannel(Queue queueChannel, FanoutExchange exchangeChannel){
        return BindingBuilder.bind(queueChannel).to(exchangeChannel);
    }

    //-----------------------------------GROUP_CHANNEL_TAG----------------------------------------------------------------

    /**
     * 声明交换机
     */
    @Bean
    public FanoutExchange exchangeGroupChannel(){
        return new FanoutExchange(GROUP_CHANNEL_TAG);
    }

    /**
     * 声明队列
     */
    @Bean
    public Queue queueGroupChannel(){
        return new Queue(GROUP_CHANNEL_TAG + CHANNEL);
    }

    /**
     * 绑定队列和交换机
     */
    @Bean
    public Binding bindingGroupChannel(Queue queueGroupChannel, FanoutExchange exchangeGroupChannel){
        return BindingBuilder.bind(queueGroupChannel).to(exchangeGroupChannel);
    }
}

