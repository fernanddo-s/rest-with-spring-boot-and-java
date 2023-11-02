package com.example.project.service;

import com.example.project.model.Person;
import org.springframework.stereotype.Service;

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
}
