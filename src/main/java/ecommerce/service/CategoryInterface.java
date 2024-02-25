package ecommerce.service;


import ecommerce.dto.CategoryDto;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryInterface {

    ResponseEntity<List<CategoryDto>> getAllCategories(Integer offset, Integer pageSize);
    ResponseEntity<String> addCategory(CategoryDto categoryDto);
    ResponseEntity<String> deleteCategory(String category);
    ResponseEntity<String> updateCategory(String category);

}
