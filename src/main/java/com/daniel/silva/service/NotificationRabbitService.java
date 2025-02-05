package com.daniel.silva.service;

import com.daniel.silva.dto.AgendarDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@Service
@AllArgsConstructor
public class NotificationRabbitService {

    private RabbitTemplate rabbitTemplate;

    public void sendNotification(AgendarDTO agendarDTO , String exchange) {
        try {
            rabbitTemplate.convertAndSend(exchange, "", agendarDTO);
        } catch (RuntimeException exception) {
            throw new RuntimeException(exception);
        }

    }

}
