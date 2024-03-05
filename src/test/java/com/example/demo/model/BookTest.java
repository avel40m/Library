package com.example.demo.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookTest {
    @Test
    void bookModelAndGetting(){
        var id = 1L;
        var name = "Book title";
        var description = "Book description";
        var book = new Book(1L,null,"Book title", "Book description", null, null);
        var bookTwo = new Book(1L,null,"Book title", "Book description", null, null);

        var hashCodeOne = book.hashCode();
        var hashCodeTwo = bookTwo.hashCode();

        assertEquals(id, book.getId());
        assertEquals(name, book.getName());
        assertEquals(description, book.getDescription());
        assertEquals("Book(id=1, isbn=null, name=Book title, description=Book description, category=null, stock=null)",book.toString());
        assertEquals(hashCodeOne, hashCodeTwo);
        assertFalse(bookTwo.equals(null));
        assertFalse(bookTwo.equals(""));
        assertTrue(book.equals(bookTwo));
    }

    @Test
    void categoryModelAndSetting(){
        var category = new Category();
        var book = new Book();
        var id = 1L;
        var name = "Title";
        var description = "Description";

        book.setId(id);
        book.setName(name);
        book.setDescription(description);
        book.setCategory(category);
        assertEquals(id, book.getId());
        assertEquals(name, book.getName());
        assertEquals(description, book.getDescription());
        assertEquals(category, book.getCategory());
    }
}
