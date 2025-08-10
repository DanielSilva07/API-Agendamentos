package com.daniel.silva.service;

import com.daniel.silva.domain.model.AgendarModel;
import com.daniel.silva.dto.AgendarDtoRequest;
import com.daniel.silva.dto.AgendarDtoResponse;
import com.daniel.silva.exceptions.AgendamentoJaExiste;
import com.daniel.silva.repository.AgendarRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class AgendarService {

    private final Logger logger = LoggerFactory.getLogger(AgendarService.class);
    private final AgendarRepository repository;

    public AgendarService(AgendarRepository repository){
        this.repository=repository;

    }

    @Transactional
    public AgendarModel save(AgendarDtoRequest agendarDtoRequest) {
        try {
            Optional<AgendarModel>existente = repository.findByNome(agendarDtoRequest.nome());
            if (existente.isPresent()) {
                logger.error("Nome já cadastrado: {}", agendarDtoRequest.nome());
                throw new AgendamentoJaExiste("Nome ja cadastrado: " + agendarDtoRequest.nome());
            }
            return repository.save(
                    AgendarModel.builder()
                            .nome(agendarDtoRequest.nome())
                            .data(agendarDtoRequest.data())
                            .descricao(agendarDtoRequest.descricao())
                            .localDateTime(LocalDateTime.now())
                            .email(agendarDtoRequest.email())
                            .build()
            );
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Erro ao salvar agendamento: {}", e.getMessage(), e);
            throw new AgendamentoJaExiste("Erro ao processar o agendamento");
        }
    }


    public List<AgendarDtoResponse> getAll(){
        return repository.findAll().stream()
                .map(AgendarModel -> AgendarDtoResponse.builder()
                        .id(AgendarModel.getId())
                        .nome(AgendarModel.getNome())
                        .descricao(AgendarModel.getDescricao())
                        .data(AgendarModel.getData())
                        .email(AgendarModel.getEmail())
                        .localDateTime(AgendarModel.getLocalDateTime())
                        .build()).toList();
    }

    public ResponseEntity<AgendarModel> update(Long id ,AgendarDtoRequest agendarDtoRequest){
        if (!repository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(repository.save(
                AgendarModel.builder()
                        .id(id)
                        .nome(agendarDtoRequest.nome())
                        .data(agendarDtoRequest.data())
                        .descricao(agendarDtoRequest.descricao())
                        .localDateTime(LocalDateTime.now())
                        .email(agendarDtoRequest.email())
                        .build()
        ));
    }

    public ResponseEntity<Object> deleteById(Long id) {
        return repository.findById(id)
                .map(taskToDelete ->{
                    repository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }



}


