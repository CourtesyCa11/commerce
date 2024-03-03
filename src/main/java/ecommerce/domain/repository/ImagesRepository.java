package ecommerce.domain.repository;



import ecommerce.domain.entities.Images;
import ecommerce.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImagesRepository  extends JpaRepository<Images,Integer> {
    List<Images> findByproduct(Product product);
    //List<Images> findAllByProduct();
}
