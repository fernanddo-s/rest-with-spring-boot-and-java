package com.example.project.service;

import com.example.project.controllers.BookController;
import com.example.project.data.dto.v1.BookDTO;
import com.example.project.exceptions.RequiredObjectIsNullException;
import com.example.project.exceptions.ResourceNotFoundException;
import com.example.project.mapper.ObjectsMapper;
import com.example.project.model.Book;
import com.example.project.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookService {

    @Autowired
    BookRepository repository;

    private Logger logger = Logger.getLogger(BookService.class.getName());

    public BookDTO findById(Long id){
        logger.info("Finding one book!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records for this ID!"));
        var dto =  ObjectsMapper.parseObject(entity, BookDTO.class);
        dto.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
        return dto;
    }

    public List<BookDTO> findAll(){
        logger.info("Finding all people!");
        var books = ObjectsMapper.parseListObjects(repository.findAll(), BookDTO.class);
        books.stream().forEach(p -> p.add(linkTo(methodOn(BookController.class).findById(p.getId())).withSelfRel()));
        return books;
    }

    public BookDTO create(BookDTO book){
        if (book == null) throw  new RequiredObjectIsNullException();
        logger.info("Creating one book!");
        var entity = ObjectsMapper.parseObject(book, Book.class);
        var dto = ObjectsMapper.parseObject(repository.save(entity), BookDTO.class);
        dto.add(linkTo(methodOn(BookController.class).findById(dto.getId())).withSelfRel());
        return dto;
    }

    public BookDTO update(BookDTO book){
        if (book == null) throw  new RequiredObjectIsNullException();
        logger.info("Updating one book!");
        var entity = repository.findById(book.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        entity.setAuthor(book.getAuthor());
        entity.setLaunchDate(book.getLaunchDate());
        entity.setPrice(book.getPrice());
        entity.setTitle(book.getTitle());
        var dto = ObjectsMapper.parseObject(repository.save(entity), BookDTO.class);
        dto.add(linkTo(methodOn(BookController.class).findById(dto.getId())).withSelfRel());
        return dto;
    }

    public void delete(Long id){
        logger.info("Deleting one book!");
        var book = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records for this ID!"));
        repository.delete(book);
    }

}
