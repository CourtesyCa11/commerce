package ecommerce.service;

import ecommerce.domain.entities.Product;
import ecommerce.dto.ProductDto;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ProductInterface {

    ResponseEntity<ProductDto> getProduct(Integer productId);
    ResponseEntity<String> addProduct(ProductDto product);
    ResponseEntity<String> deleteProduct(Integer productId);
    ResponseEntity<String> updateProduct(ProductDto product);
    ResponseEntity<List<ProductDto>> getAllProducts(Integer offset, Integer pageSize);

    ResponseEntity<List<ProductDto>> getAllProductsSorted(String sorting);

    ResponseEntity<List<ProductDto>> getProductsSorted(Integer offset, Integer pageSize, String sorting);

    ResponseEntity<Map<String,Object>> getProductsFilteredAndSorted(Double from, Double to, String Category, Integer offset);
}
