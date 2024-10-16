package com.daniel.silva.controller;

import com.daniel.silva.dto.AgendarDTO;
import com.daniel.silva.model.AgendarModel;
import com.daniel.silva.repository.AgendarRepository;
import com.daniel.silva.service.AgendarService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;


@RestController
public class AgendarController {

    private final AgendarService service;
    private final AgendarRepository repository;

    public AgendarController(AgendarService service,
                             AgendarRepository repository){
        this.service=service;
        this.repository =repository;
    }

    @PostMapping("/save")
    public ResponseEntity<AgendarModel> salvar(@RequestBody @Valid  AgendarDTO agendarDTO){

        var agendarModel = new AgendarModel();
        agendarModel.setNome(agendarDTO.nome());
        agendarModel.setDiaMes(agendarDTO.diaMes());
        agendarModel.setDescricao(agendarDTO.descricao());
        agendarModel.setLocalDateTime(LocalDateTime.now());

        return ResponseEntity.status(201).body(service.save(agendarModel));
    }

    @GetMapping("/teste")
    public String teste(){
        return " MIAMI HEAT **  ";
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<AgendarModel>> getAll(){
        return ResponseEntity.ok().body(service.getAll());
    }


    @PutMapping( path = "/{id}")
    public ResponseEntity<AgendarModel> replace(@PathVariable(value = "id") String id , @RequestBody AgendarDTO agendarDTO ){
       return service.update(id ,agendarDTO);

    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable (value = "id") String id) {
        return service.deleteById(id);


    }
}