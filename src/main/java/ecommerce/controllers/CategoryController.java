package ecommerce.controllers;


import ecommerce.dto.CategoryDto;
import ecommerce.service.CategoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    CategoryInterface service;

    @GetMapping(value = {"/categories/{offset}/{pageSize}"})
    public ResponseEntity<List<CategoryDto>> getAllCategories(@PathVariable Integer offset, @PathVariable Integer pageSize) {
        return service.getAllCategories( offset, pageSize);
    }

    @PostMapping(value = {"/category/add"})
    public ResponseEntity<String> addCategory(@RequestBody CategoryDto categoryDto) {
        return service.addCategory(categoryDto);
    }

    @DeleteMapping(value = {"/category/delete/{category}"})
    public ResponseEntity<String> deleteCategory(@PathVariable String category) {
        return service.deleteCategory(category);
    }


    public ResponseEntity<String> updateCategory(String category) {
        return null;
    }
}
