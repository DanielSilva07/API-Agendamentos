package com.daniel.silva.service;

import com.daniel.silva.domain.model.AgendarModel;
import com.daniel.silva.dto.AgendarDtoRequest;
import com.daniel.silva.infra.rabbitmq.NotificationRabbitService;
import com.daniel.silva.repository.AgendarRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class AgendarServiceTest {

    @InjectMocks
    private AgendarService agendarService;

    @Mock
    private NotificationRabbitService notificationRabbitService;

    @Mock
    private AgendarRepository agendarRepository;

    @Test
    public void deveSalvarAgendamento() {
        //TODO

        AgendarDtoRequest agendarDtoRequest = new AgendarDtoRequest(
                "daniel",
                "exemple.com.br",
                "30/10/2025",
                "Teste"
        );

        agendarService.save(agendarDtoRequest);
        verify(agendarRepository, times(1)).save(any(AgendarModel.class));
        verify(notificationRabbitService, times(1)).sendNotification(any(), any());

    }




}
