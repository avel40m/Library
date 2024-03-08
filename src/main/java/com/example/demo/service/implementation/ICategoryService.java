package com.example.demo.service.implementation;

import com.example.demo.exception.CategoryException;
import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ICategoryService implements CategoryService {

    private final Logger logger = LoggerFactory.getLogger(ICategoryService.class);

    private final CategoryRepository categoryRepository;
    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<List<Category>> searchCategory() {
        try{
            logger.info("Bringing all the category");
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(categoryRepository.findAll());
        } catch (CategoryException e) {
            logger.error("Error: " + e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @Override
     @Transactional(readOnly = true)
    public ResponseEntity<Category> searchById(Long categoryId) {
        try{
            logger.info("Search id for category");
            Optional<Category> category = categoryRepository.findById(categoryId);
            if (category.isEmpty()){
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .build();
            }
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(category.get());
        } catch (CategoryException e){
            logger.error("Error: " + e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @Override
    public ResponseEntity<Category> saveCategory(Category category) {
        try{
            logger.info("Category save exist");
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(categoryRepository.save(category));
        } catch (CategoryException e){
            logger.error("Error: " + e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @Override
    public ResponseEntity<Void> editCategory(Category category) {
        Optional<Category> searchCategory = categoryRepository.findById(category.getId());

        if(searchCategory.isEmpty()){
            logger.warn("Id category not exist in database");
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        try{
            categoryRepository.save(category);
            logger.info("Update category");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (CategoryException e){
            logger.error("Error: " + e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @Override
    public ResponseEntity<Void> deleteCategory(Long categoryId) {
        try {
            categoryRepository.deleteById(categoryId);
            logger.info("Category delete by id");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (CategoryException e){
            logger.error("Error: " + e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
