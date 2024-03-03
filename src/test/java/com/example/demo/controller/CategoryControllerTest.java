package com.example.demo.controller;

import com.example.demo.model.Category;
import com.example.demo.service.CategoryService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CategoryControllerTest {
    @Mock
    private CategoryService categoryService;
    @InjectMocks
    private CategoryController categoryController;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllCategoryTest(){
        List<Category> categoryList = new ArrayList<>();
        when(categoryService.searchCategory()).thenReturn(new ResponseEntity<>(categoryList, HttpStatus.OK));
        var response = categoryController.getAllCategory();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCode().value());
        assertEquals(categoryList, response.getBody());
    }

    @Test
    void searchCategoryByIdTest(){
        var category = new Category(1L,"Title category", "Description category");
        when(categoryService.searchById(1L)).thenReturn(new ResponseEntity<>(category, HttpStatus.OK));
        var response = categoryController.searchById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCode().value());
        assertEquals(category, response.getBody());
    }

    @Test
    void saveCategoryTest(){
        var category = new Category(1L,"Title category", "Description category");
        when(categoryService.saveCategory(any())).thenReturn(new ResponseEntity<>(category, HttpStatus.CREATED));
        var response = categoryController.saveCategory(any());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(201, response.getStatusCode().value());
        assertEquals(category, response.getBody());
    }

    @Test
    void updateCategoryTest(){
        when(categoryService.editCategory(any())).thenReturn(new ResponseEntity<>(null, HttpStatus.NO_CONTENT));
        var response = categoryController.updateCategory(any());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals(204, response.getStatusCode().value());
        assertNull(response.getBody());
    }

    @Test
    void deleteCategoryTest(){
        when(categoryService.deleteCategory(any())).thenReturn(new ResponseEntity<>(null, HttpStatus.NO_CONTENT));
        var response = categoryController.deleteCategory(any());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertEquals(204, response.getStatusCode().value());
        assertNull(response.getBody());
    }
}
