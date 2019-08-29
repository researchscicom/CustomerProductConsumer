package com.consumer.spring.service;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerServiceImpl implements ConsumerService {

    private static final String EXCHANGE_NAME = "customer.direct";

    @Override
    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(value = "customer.queue",durable = "true"),
                    exchange = @Exchange(value = EXCHANGE_NAME),
                    key = "customer.routingkey")
    )
    public void consumerMessage(Object data) {
        System.out.println(" [x] Consumed  '" + data + "'");
    }
}
