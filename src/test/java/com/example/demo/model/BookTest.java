package com.example.demo.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookTest {
    @Test
    void bookModelAndGetting(){
        var id = 1L;
        var name = "Title";
        var description = "Description";
        var category = new Category(id, name, description);
        var book = new Book(id,name,description, category);
        var bookTwo = new Book(id,name,description, category);

        var hashCodeOne = book.hashCode();
        var hashCodeTwo = bookTwo.hashCode();

        assertEquals(id, book.getId());
        assertEquals(name, book.getName());
        assertEquals(description, book.getDescription());
        assertEquals(category, book.getCategory());
        assertEquals("Book(id=1, name=Title, description=Description, category=Category(id=1, name=Title, description=Description))",book.toString());
        assertEquals(hashCodeOne, hashCodeTwo);
        assertTrue(category.equals(category));
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
