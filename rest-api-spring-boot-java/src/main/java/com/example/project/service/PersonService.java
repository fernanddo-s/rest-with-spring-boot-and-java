package com.example.project.service;

import com.example.project.data.dto.v1.PersonDTO;
import com.example.project.data.dto.v2.PersonDTOV2;
import com.example.project.exceptions.ResourceNotFoundException;
import com.example.project.mapper.ObjectsMapper;
import com.example.project.model.Person;
import com.example.project.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

        PersonDTO p = new PersonDTO();
        p.setFirstName("Fernando");
        p.setLastName("Sousa");
        p.setAddress("Quixadá - Ceará - Brasil");
        p.setGender("Male");
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records for this ID!"));
        return ObjectsMapper.parseObject(entity, PersonDTO.class);
    }

    public List<PersonDTO> findAll(){
        logger.info("Finding all people!");
        return ObjectsMapper.parseListObjects(repository.findAll(), PersonDTO.class);
    }

    public PersonDTO create(PersonDTO person){
        logger.info("Creating one person!");
        var entity = ObjectsMapper.parseObject(person, Person.class);
        var dto = ObjectsMapper.parseObject(repository.save(entity), PersonDTO.class);
        return dto;
    }

    public PersonDTOV2 createV2(PersonDTOV2 person){
        logger.info("Creating one person with V2!");
        var entity = ObjectsMapper.parseObject(person, Person.class);
        var dto = ObjectsMapper.parseObject(repository.save(entity), PersonDTOV2.class);
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
        return dto;
    }

    public void delete(Long id){
        logger.info("Deleting one person!");
        var person = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records for this ID!"));
        repository.delete(person);
    }

}
