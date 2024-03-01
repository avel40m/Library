package com.example.demo.service;

import com.example.demo.model.Category;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService {
    ResponseEntity<List<Category>> searchCategory();
    ResponseEntity<Category> searchById(Long categoryId);
    ResponseEntity<Category> saveCategory(Category category);
    ResponseEntity<Void> editCategory(Category category);
    ResponseEntity<Void> deleteCategory(Long categoryId);
}
