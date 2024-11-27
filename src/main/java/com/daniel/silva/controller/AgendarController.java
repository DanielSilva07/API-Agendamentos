package com.daniel.silva.controller;

import com.daniel.silva.dto.AgendarDTO;
import com.daniel.silva.model.AgendarModel;
import com.daniel.silva.service.AgendarService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class AgendarController {

    private final AgendarService service;

    public AgendarController(AgendarService service){
        this.service=service;

    }

    @PostMapping("/save")
    public ResponseEntity<AgendarModel> salvar(@RequestBody @Valid  AgendarDTO agendarDTO){
        return ResponseEntity.status(201).body(service.save(agendarDTO));
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