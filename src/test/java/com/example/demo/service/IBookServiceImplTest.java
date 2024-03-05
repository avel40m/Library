package com.example.demo.service;

import com.example.demo.dto.BookDto;
import com.example.demo.exception.BookException;
import com.example.demo.model.Book;
import com.example.demo.model.Category;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class IBookServiceImplTest {
    @Mock
    private BookRepository bookRepository;
    @Mock
    private CategoryRepository categoryRepository;
    @InjectMocks
    private IBookService bookService;

    private final Category category = new Category(1L, "Title category","Description category");
    private final Book book = new Book(1L,123L,"Book title", "Book description", category, null);


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void getAllBooksTest(){
        List<Book> listBooks = new ArrayList<>();
        listBooks.add(book);
        when(bookRepository.findAll()).thenReturn(listBooks);
        var response = bookService.allBooks();
        assertEquals(200, response.getStatusCode().value());
        verify(bookRepository,timeout(1)).findAll();
    }

    @Test
    void getAllBookException() throws BookException{
        when(bookRepository.findAll()).thenThrow(new BookException("Error"));
        var response = bookService.allBooks();
        assertEquals(500, response.getStatusCode().value());
    }

    @Test
    void searchBookById(){
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));
        var response = bookService.searchById(1L);
        assertEquals(200, response.getStatusCode().value());
        verify(bookRepository,timeout(1)).findById(1L);
    }

    @Test
    void searchBookByIdNotFound(){
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());
        var response = bookService.searchById(1L);
        assertEquals(404, response.getStatusCode().value());
    }

    @Test
    void searchBookByIdException() throws BookException{
        when(bookRepository.findById(1L)).thenThrow(new BookException("Error"));
        var response = bookService.searchById(1L);
        assertEquals(500, response.getStatusCode().value());
    }

    @Test
    void saveBookTest(){
        var bookDto = new BookDto(123L,"title", "description", 1L,10);
        var saveBook = new Book(null, 123L,bookDto.getName(),bookDto.getDescription(),category,null);
        when(categoryRepository.findById(category.getId())).thenReturn(Optional.of(category));
        when(bookRepository.save(any(Book.class))).thenReturn(saveBook);
        var response = bookService.saveBook(bookDto);
        assertEquals(201, response.getStatusCode().value());
        verify(bookRepository,timeout(1)).save(any());
    }

    @Test
    void saveBookCategoryNotFound(){
        var bookDto = new BookDto(123L,"title", "description", 1L,10);
        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());
        var response = bookService.saveBook(bookDto);
        assertEquals(404, response.getStatusCode().value());
    }

    @Test
    void saveBookException() throws BookException{
        var bookDto = new BookDto(123L,"title", "description", 1L,10);
        when(categoryRepository.findById(category.getId())).thenReturn(Optional.of(category));
        when(bookRepository.save(any())).thenThrow(new BookException("Error"));
        var response = bookService.saveBook(bookDto);
        assertEquals(500, response.getStatusCode().value());
    }

    @Test
    void updateBookTest(){
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.of(category));
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));
        when(bookRepository.save(any())).thenReturn(book);

        var response = bookService.updateBook(book);
        assertEquals(204, response.getStatusCode().value());

        verify(categoryRepository,timeout(1)).findById(1L);
        verify(bookRepository,timeout(1)).findById(1L);
        verify(bookRepository,timeout(1)).save(book);
    }

    @Test
    void updateBookCategoryIdNotFound(){
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.empty());
        var response = bookService.updateBook(book);
        assertEquals(404, response.getStatusCode().value());
    }

    @Test
    void updateBookIdNotFound(){
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.of(category));
        when(bookRepository.findById(anyLong())).thenReturn(Optional.empty());
        var response = bookService.updateBook(book);
        assertEquals(404, response.getStatusCode().value());
    }

    @Test
    void updateBookException() throws BookException{
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.of(category));
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));
        when(bookRepository.save(any())).thenThrow(new BookException("Error"));
        var response = bookService.updateBook(book);
        assertEquals(500, response.getStatusCode().value());
    }

    @Test
    void deleteBookTest(){
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        var response = bookService.deleteBook(1L);
        assertEquals(204, response.getStatusCode().value());
        verify(bookRepository,timeout(1)).deleteById(1L);
    }

    @Test
    void deleteBookByIdNoyFound(){
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());
        var response = bookService.deleteBook(1L);
        assertEquals(404, response.getStatusCode().value());
    }

    @Test
    void deleteBookByIdException() throws BookException{
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        doThrow(new BookException("Error")).when(bookRepository).deleteById(1L);
        var response = bookService.deleteBook(1L);
        assertEquals(500, response.getStatusCode().value());
    }
}
