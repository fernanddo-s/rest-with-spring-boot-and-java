package com.example.project.service;

import com.example.project.exceptions.ResourceNotFoundException;
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

    public Person findById(Long id){
        logger.info("Finding one person!");

        Person p = new Person();
        p.setFirstName("Fernando");
        p.setLastName("Sousa");
        p.setAddres("Quixadá - Ceará - Brasil");
        p.setGender("Male");
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records for this ID!"));
    }

    public List<Person> findAll(){
        logger.info("Finding all people!");
        return repository.findAll();
    }

    public Person create(Person person){
        logger.info("Creating one person!");
        return repository.save(person);
    }

    public Person update(Person person){
        logger.info("Updating one person!");
        Person entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records for this ID!"));
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddres(person.getAddres());
        entity.setGender(person.getGender());
        return repository.save(person);
    }

    public void delete(Long id){
        logger.info("Deleting one person!");
        Person person = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records for this ID!"));
        repository.delete(person);
    }

}
