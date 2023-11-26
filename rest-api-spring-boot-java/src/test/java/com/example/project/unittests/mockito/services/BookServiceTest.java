package com.example.project.unittests.mockito.services;

import com.example.project.data.dto.v1.BookDTO;
import com.example.project.exceptions.RequiredObjectIsNullException;
import com.example.project.model.Book;
import com.example.project.repositories.BookRepository;
import com.example.project.service.BookService;
import com.example.project.unittests.mapper.mocks.MockBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    MockBook input;

    @InjectMocks
    private BookService service;

    @Mock
    BookRepository repository;

    @BeforeEach
    void setUpMocks() throws Exception{
        input = new MockBook();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindById(){
        Book entity = input.mockEntity(1);
        entity.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        var result = service.findById(1L);
        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
        assertEquals("Author Test1",result.getAuthor());
        assertNotNull(result.getLaunchDate());
        assertEquals(25D,result.getPrice());
        assertEquals("Title Test1",result.getTitle());
    }

    @Test
    void testCreate(){
        Book entity = input.mockEntity(1);

        Book persisted = entity;
        persisted.setId(1L);

        BookDTO dto = input.mockDTO(1);
        dto.setId(1L);
        when(repository.save(entity)).thenReturn(persisted);

        var result = service.create(dto);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\""));
        assertEquals("Author Test1",result.getAuthor());
        assertNotNull(result.getLaunchDate());
        assertEquals(25D,result.getPrice());
        assertEquals("Title Test1",result.getTitle());
    }

    @Test
    void testCreateWithNullBook(){
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
            service.create(null);
        });
        String expectedMessage = "It is not allowed to persist a null object!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testUpdate(){
        Book entity = input.mockEntity(1);
        entity.setId(1L);

        Book persisted = entity;
        persisted.setId(1L);

        BookDTO dto = input.mockDTO(1);
        dto.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(repository.save(entity)).thenReturn(persisted);

        var result = service.update(dto);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
        assertEquals("Author Test1",result.getAuthor());
        assertNotNull(result.getLaunchDate());
        assertEquals(25D,result.getPrice());
        assertEquals("Title Test1",result.getTitle());
    }

    @Test
    void testUpdateWithNullBook(){
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
            service.update(null);
        });
        String expectedMessage = "It is not allowed to persist a null object!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testDelete(){
        Book entity = input.mockEntity(1);
        entity.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        var result = service.findById(1L);
    }

    @Test
    void testFindAll(){
        List<Book> list = input.mockEntityList();
        when(repository.findAll()).thenReturn(list);
        var books = service.findAll();
        assertNotNull(books);
        assertEquals(14, books.size());

        var bookOne = books.get(1);
        assertNotNull(bookOne);
        assertNotNull(bookOne.getId());
        assertNotNull(bookOne.getLinks());
        assertTrue(bookOne.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
        assertEquals("Author Test1",bookOne.getAuthor());
        assertNotNull(bookOne.getLaunchDate());
        assertEquals(25D,bookOne.getPrice());
        assertEquals("Title Test1",bookOne.getTitle());

        var bookFour = books.get(4);
        assertNotNull(bookFour);
        assertNotNull(bookFour.getId());
        assertNotNull(bookFour.getLinks());
        assertTrue(bookFour.toString().contains("links: [</api/book/v1/4>;rel=\"self\"]"));
        assertEquals("Author Test4",bookFour.getAuthor());
        assertNotNull(bookFour.getLaunchDate());
        assertEquals(25D,bookFour.getPrice());
        assertEquals("Title Test4",bookFour.getTitle());

        var bookSeven = books.get(7);
        assertNotNull(bookSeven);
        assertNotNull(bookSeven.getId());
        assertNotNull(bookSeven.getLinks());
        assertTrue(bookSeven.toString().contains("links: [</api/book/v1/7>;rel=\"self\"]"));
        assertEquals("Author Test7",bookSeven.getAuthor());
        assertNotNull(bookSeven.getLaunchDate());
        assertEquals(25D,bookSeven.getPrice());
        assertEquals("Title Test7",bookSeven.getTitle());
    }

}
