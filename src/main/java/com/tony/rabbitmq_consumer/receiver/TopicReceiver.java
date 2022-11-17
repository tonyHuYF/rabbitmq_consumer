package com.tony.rabbitmq_consumer.receiver;

import cn.hutool.json.JSONUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RabbitListener(queues = "topic.man")
public class TopicReceiver {

    @RabbitHandler
    public void  process(Map map){
        System.out.println("TopicReceiver 消费者接收到消息: " + JSONUtil.toJsonStr(map));
    }
}
