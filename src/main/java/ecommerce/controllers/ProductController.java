package ecommerce.controllers;

import ecommerce.dto.ProductDto;

import ecommerce.service.ProductInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ProductController  {


    @Autowired
    ProductInterface service;

    @GetMapping(value = {"/product/get/{productId}"})
    public ResponseEntity<ProductDto> getProduct(@PathVariable Integer productId) {
        return service.getProduct(productId);
    }

    @PostMapping(value = {"/product/add"})
    public ResponseEntity<String> addProduct(@RequestBody ProductDto product) {
        return service.addProduct(product);
    }

    @DeleteMapping(value = {"/product/delete/{productId}"})
    public ResponseEntity<String> deleteProduct(@PathVariable Integer productId) {
        return service.deleteProduct(productId);
    }

    @PutMapping(value = {"/product/update"})
    public ResponseEntity<String> updateProduct(@RequestBody ProductDto product) {
        return service.updateProduct(product);
    }

    @GetMapping(value = {"/products/{offset}/{pageSize}"})
    public ResponseEntity<List<ProductDto>> getProducts(@PathVariable Integer offset, @PathVariable Integer pageSize){
        return service.getAllProducts(offset,pageSize);
    }

    @GetMapping(value = {"/products/sorted/{sortedBy}"})
    public ResponseEntity<List<ProductDto>> getAllProductsSorted(@PathVariable String sortedBy){
        return service.getAllProductsSorted(sortedBy);
    }

    @GetMapping(value = {"/products/paginationAndSorted/{offset}/{pageSize}/{sortedBy}"})
    public ResponseEntity<List<ProductDto>> getProductsSorted(@PathVariable Integer offset, @PathVariable Integer pageSize, @PathVariable String sortedBy){
        return service.getProductsSorted(offset,pageSize,sortedBy);
    }
}
