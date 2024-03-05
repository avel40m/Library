package com.example.demo.service;

import com.example.demo.dto.BookDto;
import com.example.demo.exception.BookException;
import com.example.demo.model.Book;
import com.example.demo.model.Category;
import com.example.demo.model.Stock;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class IBookService implements BookService{
    private final Logger logger = LoggerFactory.getLogger(IBookService.class);
    @Autowired
    private final BookRepository bookRepository;
    @Autowired
    private final CategoryRepository categoryRepository;

    @Override
    public ResponseEntity<List<Book>> allBooks() {
        try{
            logger.info("Getting all books");
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(bookRepository.findAll());
        } catch (BookException e) {
            logger.error("Error: " + e);
            return ResponseEntity
                    .internalServerError()
                    .build();
        }
    }

    @Override
    public ResponseEntity<Book> searchById(Long bookId) {
        try {
            Optional<Book> bookOptional = bookRepository.findById(bookId);
            if(bookOptional.isEmpty()){
                logger.warn("Book not found");
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .build();
            }
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(bookOptional.get());
        }  catch (BookException e){
            logger.error("Error: " + e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @Override
    public ResponseEntity<Book> saveBook(BookDto book) {
        try{
            Optional<Category> categoryOptional = categoryRepository.findById(book.getCategoryId());
            if (categoryOptional.isEmpty()){
                logger.warn("Category not found");
                return ResponseEntity.notFound().build();
            }
            if (book.getQuantity() <= 0){
                logger.warn("The quantity field was not completed");
                return ResponseEntity.notFound().build();
            }
            var stock = new Stock(null, book.getQuantity());
            var newBook = new Book(null,book.getIsbn(),book.getName(), book.getDescription(), categoryOptional.get(), stock);

            logger.info("Book saved successful");
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(bookRepository.save(newBook));
        } catch (BookException e){
            logger.error("Error: " + e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @Override
    public ResponseEntity<Void> updateBook(Book book) {
        try{
            Optional<Category> categoryOptional = categoryRepository.findById(book.getCategory().getId());
            if (categoryOptional.isEmpty()){
                logger.warn("Category not found");
                return ResponseEntity.notFound().build();
            }
            Optional<Book> bookOptional = bookRepository.findById(book.getId());
            if (bookOptional.isEmpty()){
                logger.info("Book not found");
                return ResponseEntity
                        .notFound()
                        .build();
            }
            bookRepository.save(bookOptional.get());
            logger.info("Book updated successful");
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build();
        } catch (BookException e){
            logger.error("Error: " + e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @Override
    public ResponseEntity<Void> deleteBook(Long bookId) {
        try{
            Optional<Book> bookOptional = bookRepository.findById(bookId);
            if (bookOptional.isEmpty()){
                logger.warn("Book not found");
                return ResponseEntity.notFound().build();
            }
            bookRepository.deleteById(bookId);
            logger.info("Book deleted successful");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (BookException e){
            logger.error("Error: " + e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
