package ecommerce.domain.repository;


import ecommerce.domain.entities.Category;
import ecommerce.domain.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {





   // List<Product> findByActualPriceGreaterThanEqualAndActualPriceLessThanEqual(Double startActualPrice, Double endActualPrice);

    Page<Product> findByActualPriceGreaterThanEqualAndActualPriceLessThanEqual(Double from, Double to, PageRequest of);


    Page<Product> findByActualPriceGreaterThanEqualAndActualPriceLessThanEqualAndCategory(Double from, Double to, Category category, PageRequest of);

    Page<Product> findByCategory(Category category, PageRequest of);
}
