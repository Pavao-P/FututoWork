package com.futurowork.messaging;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Component
public class EventProducer {
    private final AmqpTemplate amqp;

    public EventProducer(AmqpTemplate amqp) {
        this.amqp = amqp;
    }

    public void publishOnboard(Object payload) {
        amqp.convertAndSend(RabbitConfig.EXCHANGE, RabbitConfig.ROUTING_ONBOARD, payload);
    }
}
