package ecommerce.service;

import ecommerce.domain.entities.Category;
import ecommerce.domain.repository.CategoryRepository;

import ecommerce.dto.CategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryImpl implements CategoryInterface{

    @Autowired
    CategoryRepository repository;

    @Override
    public ResponseEntity<List<CategoryDto>> getAllCategories(Integer offset, Integer pageSize) {
        Page<Category> categories = repository.findAll(PageRequest.of(offset,pageSize));
        List<CategoryDto> dtos = categories.stream().map(category -> new CategoryDto(category.getCategory())).toList();
        return new ResponseEntity(dtos,HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<String> addCategory(CategoryDto categoryDto) {

        if(repository.existsById(categoryDto.getCategory()))
            return new ResponseEntity<>("This category already exists", HttpStatus.CONFLICT);
        Category category = new Category(categoryDto);
        repository.save(category);
        return new ResponseEntity<>("Category Successfully added", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> deleteCategory(String category) {
        return null;
    }

    @Override
    public ResponseEntity<String> updateCategory(String category) {
        return null;
    }
}
