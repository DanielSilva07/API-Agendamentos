package com.daniel.silva.controller;

import com.daniel.silva.dto.AgendarDtoRequest;
import com.daniel.silva.dto.AgendarDtoResponse;
import com.daniel.silva.domain.model.AgendarModel;
import com.daniel.silva.service.AgendarService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/agendamento")
public class AgendarController {

    private final AgendarService service;

    public AgendarController(AgendarService service){
        this.service=service;
    }

    @PostMapping()
    public ResponseEntity<AgendarModel>salvar(@RequestBody @Valid AgendarDtoRequest agendarDtoRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(agendarDtoRequest));
    }

    @GetMapping()
    public ResponseEntity<List<AgendarDtoResponse>>getAll(){
        return ResponseEntity.ok().body(service.getAll());
    }

    @PutMapping( path = "/{id}")
    public ResponseEntity<AgendarModel>replace(@PathVariable(value = "id") Long id , @RequestBody AgendarDtoRequest agendarDtoRequest){
       return service.update(id , agendarDtoRequest);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object>deleteById(@PathVariable (value = "id") Long id) {
        return service.deleteById(id);
    }
}