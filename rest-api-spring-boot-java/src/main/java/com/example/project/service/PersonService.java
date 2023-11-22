package com.example.project.service;

import com.example.project.controllers.PersonController;
import com.example.project.data.dto.v1.PersonDTO;
import com.example.project.exceptions.ResourceNotFoundException;
import com.example.project.mapper.ObjectsMapper;
import com.example.project.model.Person;
import com.example.project.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonService {

    @Autowired
    PersonRepository repository;

    private Logger logger = Logger.getLogger(PersonService.class.getName());

    public PersonDTO findById(Long id){
        logger.info("Finding one person!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records for this ID!"));
        var dto =  ObjectsMapper.parseObject(entity, PersonDTO.class);
        dto.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return dto;
    }

    public List<PersonDTO> findAll(){
        logger.info("Finding all people!");
        var persons = ObjectsMapper.parseListObjects(repository.findAll(), PersonDTO.class);
        persons.stream().forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getId())).withSelfRel()));
        return persons;
    }

    public PersonDTO create(PersonDTO person){
        logger.info("Creating one person!");
        var entity = ObjectsMapper.parseObject(person, Person.class);
        var dto = ObjectsMapper.parseObject(repository.save(entity), PersonDTO.class);
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getId())).withSelfRel());
        return dto;
    }

    public PersonDTO update(PersonDTO person){
        logger.info("Updating one person!");
        var entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        var dto = ObjectsMapper.parseObject(repository.save(entity), PersonDTO.class);
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getId())).withSelfRel());
        return dto;
    }

    public void delete(Long id){
        logger.info("Deleting one person!");
        var person = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records for this ID!"));
        repository.delete(person);
    }

}
