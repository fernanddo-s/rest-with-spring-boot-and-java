package com.example.project.controllers;

import com.example.project.data.dto.v1.PersonDTO;
import com.example.project.service.PersonService;
import com.example.project.utils.MediaTypeCustom;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/person/v1")
@Tag(name = "People", description = "Endpoints for Managing People")
public class PersonController {

    @Autowired
    private PersonService service;
    @GetMapping(produces = {MediaTypeCustom.APPLICATION_JSON, MediaTypeCustom.APPLICATION_XML, MediaTypeCustom.APPLICATION_YML})
    public List<PersonDTO> findAll(){
        return service.findAll();
    }

    @GetMapping(value = "/{id}",produces = {MediaTypeCustom.APPLICATION_JSON, MediaTypeCustom.APPLICATION_XML, MediaTypeCustom.APPLICATION_YML})
    public PersonDTO findById(@PathVariable(value = "id") Long id){
        return service.findById(id);
    }

    @PostMapping(consumes = {MediaTypeCustom.APPLICATION_JSON, MediaTypeCustom.APPLICATION_XML, MediaTypeCustom.APPLICATION_YML},
            produces = {MediaTypeCustom.APPLICATION_JSON, MediaTypeCustom.APPLICATION_XML, MediaTypeCustom.APPLICATION_YML})
    public PersonDTO create(@RequestBody PersonDTO person){
        return service.create(person);
    }

    @PutMapping(consumes = {MediaTypeCustom.APPLICATION_JSON, MediaTypeCustom.APPLICATION_XML, MediaTypeCustom.APPLICATION_YML},
            produces = {MediaTypeCustom.APPLICATION_JSON, MediaTypeCustom.APPLICATION_XML, MediaTypeCustom.APPLICATION_YML})
    public PersonDTO update(@RequestBody PersonDTO person){
        return service.update(person);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
