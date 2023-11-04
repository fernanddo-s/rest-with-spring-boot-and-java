package com.example.project.service;

import com.example.project.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {
    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonService.class.getName());

    public Person findById(String id){
        logger.info("Finding one person!");

        Person p = new Person();
        p.setId(counter.incrementAndGet());
        p.setFirstName("Fernando");
        p.setLastName("Sousa");
        p.setAddres("Quixadá - Ceará - Brasil");
        p.setGender("Male");
        return p;
    }

    public Person create(Person person){
        logger.info("Creating one person!");
        return person;
    }

    public Person update(Person person){
        logger.info("Updating one person!");
        return person;
    }

    public void delete(String id){
        logger.info("Deleting one person!");
    }

    public List<Person> findAll(){
        logger.info("Finding all people!");
        ArrayList<Person> persons = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            Person p = new Person();
            p.setId(counter.incrementAndGet());
            p.setFirstName("First Name " + i);
            p.setLastName("Last Name " + i);
            p.setAddres("Brasil");
            p.setGender("Male");
            persons.add(p);
        }
        return persons;
    }

}
