package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class BookControllerTest {
    @Mock
    private BookService bookService;
    @InjectMocks
    private BookController bookController;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void allBookTest(){
        var book = new Book(1L,null,"Book title", "Book description", null, null);
        List<Book> bookList = new ArrayList<>();
        bookList.add(book);
        when(bookService.allBooks()).thenReturn(new ResponseEntity<>(bookList, HttpStatus.OK));
        var response = bookController.allBooks();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCode().value());
        assertEquals(bookList, response.getBody());
    }

    @Test
    void searchBookByIdTest(){
        var book = new Book(1L,null,"Book title", "Book description", null, null);
        when(bookService.searchById(1L)).thenReturn(new ResponseEntity<>(book, HttpStatus.OK));
        var response = bookController.searchBookById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCode().value());
        assertEquals(book, response.getBody());
    }

    @Test
    void saveBookTest(){
        var book = new Book(1L,null,"Book title", "Book description", null, null);
        when(bookService.saveBook(any())).thenReturn(new ResponseEntity<>(book,HttpStatus.CREATED));
        var response = bookController.saveBook(any());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(201, response.getStatusCode().value());
        assertEquals(book, response.getBody());
    }

    @Test
    void updateBookTest() {
        when(bookService.updateBook(any())).thenReturn(new ResponseEntity<>(null,HttpStatus.NO_CONTENT));
        var response = bookController.updateBook(any());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals(204, response.getStatusCode().value());
        assertNull(response.getBody());
    }

    @Test
    void deleteBookTest() {
        when(bookService.deleteBook(any())).thenReturn(new ResponseEntity<>(null,HttpStatus.NO_CONTENT));
        var response = bookController.deleteBook(any());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals(204, response.getStatusCode().value());
        assertNull(response.getBody());
    }
}
