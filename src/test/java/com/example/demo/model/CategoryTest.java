package com.example.demo.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryTest {
    @Test
    void categoryModelAndGetting(){
        var id = 1L;
        var name = "Title";
        var description = "Description";
        var category = new Category(id, name, description);
        var categoryTwo = new Category(id, name, description);

        var hashCodeOne = category.hashCode();
        var hashCodeTwo = categoryTwo.hashCode();

        assertEquals(id, category.getId());
        assertEquals(name, category.getName());
        assertEquals(description, category.getDescription());
        assertEquals("Category(id=1, name=Title, description=Description)",category.toString());
        assertEquals(hashCodeOne, hashCodeTwo);
        assertTrue(category.equals(category));
        assertFalse(categoryTwo.equals(null));
        assertFalse(categoryTwo.equals(""));
        assertTrue(category.equals(categoryTwo));
    }

    @Test
    void categoryModelAndSetting(){
        var category = new Category();
        var id = 1L;
        var name = "Title";
        var description = "Description";
        category.setId(id);
        category.setName(name);
        category.setDescription(description);
        assertEquals(id, category.getId());
        assertEquals(name, category.getName());
        assertEquals(description, category.getDescription());
    }
}
