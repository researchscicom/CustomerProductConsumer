package com.consumer.spring.service;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerServiceImpl implements ConsumerService {

    private static final String EXCHANGE_NAME = "jsa.direct";

    @Override
    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(value = "jsa.queue",durable = "true"),
                    exchange = @Exchange(value = EXCHANGE_NAME),
                    key = "jsa.routingkey")
    )
    public void consumerMessage(byte[] data) {
        String consumedMessage = new String(data);
        System.out.println(" [x] Consumed  '" + consumedMessage + "'");
    }
}
