package com.example.demo.service;

import com.example.demo.exception.CategoryException;
import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ICategoryServiceImplTest {
    @Mock
    private CategoryRepository categoryRepository;
    @InjectMocks
    private ICategoryService categoryService;

    private final  Category category = new Category(1L,"pasta","Right brand");

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllCategoryTest(){
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(category);
        when(categoryRepository.findAll()).thenReturn(categoryList);
        ResponseEntity<List<Category>> response = categoryService.searchCategory();

        assertEquals(1,response.getBody().size());

        verify(categoryRepository,timeout(1)).findAll();
    }

    @Test
    void getAllCategoryException() throws CategoryException{
        when(categoryRepository.findAll()).thenThrow(new CategoryException("Error"));
        var response = categoryService.searchCategory();
        assertEquals(500,response.getStatusCode().value());
    }

    @Test
    void searchCategoryById(){
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        var response = categoryService.searchById(1L);
        assertEquals(category, response.getBody());
        assertEquals(200, response.getStatusCode().value());
        verify(categoryRepository,timeout(1)).findById(Long.valueOf(1));
    }

    @Test
    void setCategoryByIdNotExists() throws CategoryException{
        CategoryRepository categoryRepository = mock(CategoryRepository.class);
        when(categoryRepository.findById(anyLong())).thenReturn(null);
        ResponseEntity<Category> response = categoryService.searchById(123L);
        assertEquals(404, response.getStatusCode().value());
    }

    @Test
    void categoryByIdException(){
        when(categoryRepository.findById(anyLong())).thenThrow(new CategoryException("Error"));
        var response = categoryService.searchById(1L);
        assertEquals(500, response.getStatusCode().value());
    }

    @Test
    void saveCategoryTest(){
        when(categoryRepository.save(any())).thenReturn(category);
        var response = categoryService.saveCategory(category);
        assertEquals(201,response.getStatusCode().value());
        assertEquals(category,response.getBody());
        verify(categoryRepository,timeout(1)).save(category);
    }

    @Test
    void saveCategoryExceptionTest() throws CategoryException{
        when(categoryRepository.save(any())).thenThrow(new CategoryException(any()));
        var response = categoryService.saveCategory(category);
        assertEquals(500, response.getStatusCode().value());
    }

    @Test
    void editCategoryTest(){
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(categoryRepository.save(any(Category.class))).thenReturn(category);
        var response = categoryService.editCategory(category);
        assertEquals(204,response.getStatusCode().value());
        verify(categoryRepository,timeout(1)).findById(1L);
        verify(categoryRepository,timeout(1)).save(category);
    }

    @Test
    void editCategoryByIdNotFoundTest(){
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.empty());
        var response = categoryService.editCategory(category);
        assertEquals(404, response.getStatusCode().value());
    }

    @Test
    void editCategoryExceptionTest() throws CategoryException{
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(categoryRepository.save(category)).thenThrow(new CategoryException("Error"));
        var response = categoryService.editCategory(category);
        assertEquals(500, response.getStatusCode().value());
    }

    @Test
    void deleteCategory(){
        var response = categoryService.deleteCategory(1L);
        assertEquals(204, response.getStatusCode().value());
        verify(categoryRepository,timeout(1)).deleteById(1L);
    }

    @Test
    void deleteCategoryException() throws CategoryException{
        doThrow(new CategoryException("Error")).when(categoryRepository).deleteById(1L);
        var response = categoryService.deleteCategory(1L);
        assertEquals(500, response.getStatusCode().value());
    }
}
