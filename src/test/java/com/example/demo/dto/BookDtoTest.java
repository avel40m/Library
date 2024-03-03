package com.example.demo.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BookDtoTest {
    @Test
    void bookConstructorAndGetting(){
        var name = "title";
        var description = "description";
        var categoryId = 1L;
        var bookDto = new BookDto(name, description, categoryId);
        var bookDtoTwo = new BookDto(name, description, categoryId);

        var hashCodeOne = bookDto.hashCode();
        var hashCodeTwo = bookDto.hashCode();

        assertEquals(name, bookDto.getName());
        assertEquals(description, bookDto.getDescription());
        assertEquals(categoryId, bookDto.getCategoryId());
        assertEquals("BookDto(name=title, description=description, categoryId=1)",bookDto.toString());
        assertEquals(hashCodeOne, hashCodeTwo);
        assertTrue(bookDto.equals(bookDto));
        assertFalse(bookDtoTwo.equals(null));
        assertFalse(bookDtoTwo.equals(""));
        assertTrue(bookDtoTwo.equals(bookDto));
    }

    @Test
    void bookConstructorAndSetting(){
        var bookDto = new BookDto();
        var name = "title";
        var description = "description";
        var categoryId = 1L;
        bookDto.setName(name);
        bookDto.setDescription(description);
        bookDto.setCategoryId(categoryId);
        assertEquals(name, bookDto.getName());
        assertEquals(description, bookDto.getDescription());
        assertEquals(categoryId, bookDto.getCategoryId());
    }
}
