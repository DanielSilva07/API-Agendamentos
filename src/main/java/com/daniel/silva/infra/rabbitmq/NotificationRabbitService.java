package com.daniel.silva.infra.rabbitmq;

import com.daniel.silva.dto.AgendarDtoRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class NotificationRabbitService {

    private final Logger log = LoggerFactory.getLogger(NotificationRabbitService.class);
    public final String exchange;

    private final RabbitTemplate rabbitTemplate;

    public NotificationRabbitService(@Value("${rabbitmq.ms-agendamento.exchange}") String exchange,
                                     RabbitTemplate rabbitTemplate) {
        this.exchange = exchange;
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendNotification(AgendarDtoRequest agendarDtoRequest, String exchange) {
        try {
            log.info("Enviando Notificação para o Exchange {}", exchange);
            rabbitTemplate.convertAndSend(exchange, "", agendarDtoRequest);
        } catch (RuntimeException exception) {
            throw new RuntimeException(exception);
        }

    }
}

