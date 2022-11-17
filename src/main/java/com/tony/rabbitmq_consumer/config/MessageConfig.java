package com.tony.rabbitmq_consumer.config;

import com.tony.rabbitmq_consumer.receiver.MyAckReceiver;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class MessageConfig {
    @Resource
    public CachingConnectionFactory cachingConnectionFactory;


    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer(MyAckReceiver myAckReceiver) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(cachingConnectionFactory);
        container.setConcurrentConsumers(1);
        container.setMaxConcurrentConsumers(1);
        //设置手动确认信息
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);

        //设置队列
        container.addQueues(new Queue("testDirectQueue", true));
        container.addQueues(new Queue("topic.man", true));
        container.addQueues(new Queue("topic.woman", true));

        //设置消息处理类
        container.setMessageListener(myAckReceiver);

        return container;
    }
}
