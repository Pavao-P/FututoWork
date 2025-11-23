package com.futurowork.messaging;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String EXCHANGE = "futurowork.exchange";
    public static final String QUEUE_ONBOARD = "futurowork.onboard.queue";
    public static final String ROUTING_ONBOARD = "user.onboard";

    @Bean
    public Exchange appExchange() {
        return ExchangeBuilder.topicExchange(EXCHANGE).durable(true).build();
    }

    @Bean
    public Queue onboardQueue() {
        return QueueBuilder.durable(QUEUE_ONBOARD).build();
    }

    @Bean
    public Binding declareBinding() {
        return BindingBuilder.bind(onboardQueue()).to(appExchange()).with(ROUTING_ONBOARD).noargs();
    }
}
