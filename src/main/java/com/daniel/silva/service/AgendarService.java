package com.daniel.silva.service;

import com.daniel.silva.dto.AgendarDTO;
import com.daniel.silva.model.AgendarModel;
import com.daniel.silva.repository.AgendarRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class AgendarService {

    private final AgendarRepository repository;

    public AgendarService(AgendarRepository repository){
        this.repository=repository;

    }

    public AgendarModel save(AgendarDTO agendarDTO){

        var agendarModel = new AgendarModel();
        agendarModel.setNome(agendarDTO.nome());
        agendarModel.setDiaMes(agendarDTO.diaMes());
        agendarModel.setDescricao(agendarDTO.descricao());
        agendarModel.setLocalDateTime(LocalDateTime.now());
        return repository.save(agendarModel);

    }

    public List<AgendarModel> getAll(){
        return repository.findAll();
    }

    public ResponseEntity<AgendarModel> update(String id , AgendarDTO agendarDTO){
        if (!repository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        AgendarModel agendarModel = new AgendarModel();
        agendarModel.setId(id);
        agendarModel.setNome(agendarDTO.nome());
        agendarModel.setDescricao(agendarDTO.descricao());
        agendarModel.setDiaMes(agendarDTO.diaMes());
        agendarModel.setEmail(agendarDTO.email());

        return ResponseEntity.ok(repository.save(agendarModel));

    }

    public ResponseEntity<Object> deleteById(String id) {
        return repository.findById(id)
                .map(taskToDelete ->{
                    repository.deleteById(id);
                    return ResponseEntity.noContent().build();
                }).orElse(ResponseEntity.notFound().build());
    }



    }


