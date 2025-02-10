package com.daniel.silva.service;

import com.daniel.silva.dto.AgendarDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@Service
public class NotificationRabbitService {

    private final String exchange;

    private final RabbitTemplate rabbitTemplate;

    public NotificationRabbitService(@Value("${rabbitmq.ms-agendamento.exchange}") String exchange,
                                     RabbitTemplate rabbitTemplate) {
        this.exchange = exchange;
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendNotification(AgendarDTO agendarDTO , String exchange) {
        try {
            rabbitTemplate.convertAndSend(exchange, "", agendarDTO);
        } catch (RuntimeException exception) {
            throw new RuntimeException(exception);
        }

    }

}
