package com.daniel.silva.service;

import com.daniel.silva.dto.AgendarDtoRequest;
import com.daniel.silva.dto.AgendarDtoResponse;
import com.daniel.silva.infra.rabbitmq.NotificationRabbitService;
import com.daniel.silva.model.AgendarModel;
import com.daniel.silva.repository.AgendarRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class AgendarService {

    private final NotificationRabbitService notificationRabbitService;
    private final AgendarRepository repository;

    public AgendarService(NotificationRabbitService notificationRabbitService,
                          AgendarRepository repository){
        this.notificationRabbitService = notificationRabbitService;
        this.repository=repository;

    }

    public AgendarModel save(AgendarDtoRequest agendarDtoRequest){

        var agendarModel = new AgendarModel();
        agendarModel.setNome(agendarDtoRequest.nome());
        agendarModel.setData(agendarDtoRequest.data());
        agendarModel.setDescricao(agendarDtoRequest.descricao());
        agendarModel.setLocalDateTime(LocalDateTime.now());
        agendarModel.setEmail(agendarDtoRequest.email());

        notificationRabbitService.sendNotification(agendarDtoRequest,
                "agendamento-exchange");

        return repository.save(agendarModel);


    }

    public List<AgendarDtoResponse> getAll(){
        return repository.findAll().stream()
                .map(AgendarModel -> AgendarDtoResponse.builder()
                        .id(AgendarModel.getId())
                        .nome(AgendarModel.getNome())
                        .descricao(AgendarModel.getDescricao())
                        .data(AgendarModel.getData())
                        .email(AgendarModel.getEmail())
                        .build()).toList();
    }

    public ResponseEntity<AgendarModel> update(String id ,AgendarDtoRequest agendarDtoRequest){
        if (!repository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        AgendarModel agendarModel = new AgendarModel();
        agendarModel.setId(id);
        agendarModel.setNome(agendarDtoRequest.nome());
        agendarModel.setDescricao(agendarDtoRequest.descricao());
        agendarModel.setData(agendarDtoRequest.data());
        agendarModel.setEmail(agendarDtoRequest.email());

        return ResponseEntity.ok(repository.save(agendarModel));

    }

    public ResponseEntity<Object> deleteById(String id) {
        return repository.findById(id)
                .map(taskToDelete ->{
                    repository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }



    }


