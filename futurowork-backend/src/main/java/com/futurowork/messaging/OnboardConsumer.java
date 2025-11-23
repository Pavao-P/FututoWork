package com.futurowork.messaging;

import com.futurowork.entity.MessageQueueLog;
import com.futurowork.repository.MessageQueueLogRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OnboardConsumer {

    private final MessageQueueLogRepository logRepo;

    public OnboardConsumer(MessageQueueLogRepository logRepo) { this.logRepo = logRepo; }

    @RabbitListener(queues = RabbitConfig.QUEUE_ONBOARD)
    public void handle(Object payload) {
        // gravar log simples da mensagem
        MessageQueueLog log = MessageQueueLog.builder()
                .routingKey(RabbitConfig.ROUTING_ONBOARD)
                .payload(payload.toString())
                .build();
        logRepo.save(log);
    }
}
