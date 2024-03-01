package com.example.demo.controller;

import com.example.demo.dto.BookDto;
import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/")
    public ResponseEntity<List<Book>> allBooks() {
        return bookService.allBooks();
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<Book> searchBookById(@PathVariable Long bookId){
        return bookService.searchById(bookId);
    }

    @PostMapping("/")
    public ResponseEntity<Book> saveBook(@RequestBody BookDto request){
        return bookService.saveBook(request);
    }

    @PutMapping("/")
    public ResponseEntity<Void> updateBook(@RequestBody Book request){
        return bookService.updateBook(request);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long bookId){
        return bookService.deleteBook(bookId);
    }
}
