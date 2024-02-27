package ecommerce.service;


import ecommerce.domain.entities.Category;
import ecommerce.domain.entities.Product;
import ecommerce.domain.repository.CategoryRepository;
import ecommerce.domain.repository.ImagesRepository;
import ecommerce.domain.repository.ProductRepository;
import ecommerce.dto.ProductDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class ProductImpl implements ProductInterface{


    ProductRepository productRepository;
    CategoryRepository categoryRepository;
    ImagesRepository imagesRepository;

    @Autowired
    public ProductImpl(ProductRepository productRepository, CategoryRepository categoryRepository, ImagesRepository imagesRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.imagesRepository = imagesRepository;
    }

    @Override
    public ResponseEntity<ProductDto> getProduct(Integer productId) {
        return new ResponseEntity<>(new ProductDto((productRepository.findById(productId).orElseThrow(()-> new EntityNotFoundException("Product is not found")))), HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<String> addProduct(ProductDto product) {

        Category category = categoryRepository.findById(product.getCategory()).orElseThrow( () -> new EntityNotFoundException("Category is not found"));
       // List<Images> images = imagesRepository.findAllByProduct();
        if(productRepository.existsById(product.getProductId()))
            return new ResponseEntity<>("this id already exists", HttpStatus.CONFLICT);
        Product productEntity = new Product(product.getProductId(), product.getName(), product.getActualPrice(),product.getSale(),
                product.getIsNew(),product.getRating(),product.getColor(),product.getAmount(),category,product.getDateSale());
        log.error(product.getProductId().toString());
       productRepository.save(productEntity);
        return new ResponseEntity<>("Product is successfully added", HttpStatus.CREATED);
    }

    @Override
    @Transactional
    public ResponseEntity<String> deleteProduct(Integer productId) {
        productRepository.deleteById(productId);
        return new ResponseEntity<>("Product successfully deleted",HttpStatus.ACCEPTED);
    }

    @Override
    @Transactional
    public ResponseEntity<String> updateProduct(ProductDto product) {
        return null;
    }

    @Override
    public ResponseEntity<List<ProductDto>> getAllProducts(Integer offset, Integer pageSize) {
        if(pageSize == null)
            pageSize = 15;
        Page<Product> products = productRepository.findAll(PageRequest.of(offset,pageSize));
        List<ProductDto> productDtos = products.map(ProductDto::new).toList();
        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ProductDto>> getAllProductsSorted(String sorting) {
        List<Product> products = productRepository.findAll(Sort.by(Sort.Direction.ASC,sorting));
        List<ProductDto> productDtos = products.stream().map(ProductDto::new).toList();
        return new ResponseEntity<>(productDtos, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<List<ProductDto>> getProductsSorted(Integer offset, Integer pageSize, String sorting) {
        Page<Product> products = productRepository.findAll(PageRequest.of(offset,pageSize).withSort(Sort.by(sorting)));
        List<ProductDto> productDtos = products.stream().map(ProductDto::new).toList();
        return new ResponseEntity<>(productDtos, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<List<ProductDto>> getProductsFilteredAndSorted(Double from,Double to,String Category,Integer offset) {
        if(Category == null){

            Page<Product> products = productRepository.findByActualPriceGreaterThanEqualAndActualPriceLessThanEqual( from,to,PageRequest.of(offset,15));
            List<ProductDto> productDtos = products.stream().map(ProductDto::new).toList();
            return new ResponseEntity<>(productDtos,HttpStatus.ACCEPTED);
        }
        if(from == null && to == null){
            Category category = categoryRepository.findById(Category).orElseThrow( () -> new EntityNotFoundException("Category is not found"));
            Page<Product> products = productRepository.findByCategory(category,PageRequest.of(offset,15));
            List<ProductDto> productDtos = products.stream().map(ProductDto::new).toList();
            return new ResponseEntity<>(productDtos,HttpStatus.ACCEPTED);
        }
        else {
            Category category = categoryRepository.findById(Category).orElseThrow( () -> new EntityNotFoundException("Category is not found"));
            Page<Product> products = productRepository.findByActualPriceGreaterThanEqualAndActualPriceLessThanEqualAndCategory(from,to,category,PageRequest.of(offset,15));
            List<ProductDto> productDtos = products.stream().map(ProductDto::new).toList();
            return new ResponseEntity<>(productDtos,HttpStatus.ACCEPTED);
        }
    }


//    private ProductDto toProductDto(Product product){
//        return new ProductDto(product.getProductId(),product.getCategory().getCategory(),product.getName(),product.getActualPrice(),
//                product.getSale(),product.getIsNew(),product.getRating(),product.getColor(),product.getAmount(),product.getDateSale());
//
//    }
}
