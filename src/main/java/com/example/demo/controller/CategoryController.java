package com.example.demo.controller;

import com.example.demo.model.Category;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public ResponseEntity<List<Category>> getAllCategory(){
        return categoryService.searchCategory();
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> searchById(@PathVariable Long categoryId){
        return categoryService.searchById(categoryId);
    }

    @PostMapping("/")
    public ResponseEntity<Category> saveCategory(@RequestBody Category request){
        return categoryService.saveCategory(request);
    }

    @PutMapping("/")
    public ResponseEntity<Void> updateCategory(@RequestBody Category request){
        return categoryService.editCategory(request);
    }

    @DeleteMapping("/categoryId")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId){
        return categoryService.deleteCategory(categoryId);
    }
}
