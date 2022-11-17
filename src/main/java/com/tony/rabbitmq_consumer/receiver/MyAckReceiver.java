package com.tony.rabbitmq_consumer.receiver;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

@Component
public class MyAckReceiver implements ChannelAwareMessageListener {
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            System.out.println(message.toString());

            channel.basicAck(deliveryTag, true);
        } catch (Exception e) {
            //重新把消息放回队列
            channel.basicNack(deliveryTag, true, true);
        }

    }
}
