package ecommerce.service;

import ecommerce.dto.ProductDto;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductInterface {

    ResponseEntity<ProductDto> getProduct(Integer productId);
    ResponseEntity<String> addProduct(ProductDto product);
    ResponseEntity<String> deleteProduct(Integer productId);
    ResponseEntity<String> updateProduct(ProductDto product);
    ResponseEntity<List<ProductDto>> getAllProducts(Integer offset, Integer pageSize);

    ResponseEntity<List<ProductDto>> getAllProductsSorted(String sorting);

    ResponseEntity<List<ProductDto>> getProductsSorted(Integer offset, Integer pageSize, String sorting);
}
