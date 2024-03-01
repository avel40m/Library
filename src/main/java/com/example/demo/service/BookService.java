package com.example.demo.service;

import com.example.demo.dto.BookDto;
import com.example.demo.model.Book;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookService {
    ResponseEntity<List<Book>> allBooks();
    ResponseEntity<Book> searchById(Long bookId);
    ResponseEntity<Book> saveBook(BookDto book);
    ResponseEntity<Void> updateBook(Book book);
    ResponseEntity<Void> deleteBook(Long bookId);
}
