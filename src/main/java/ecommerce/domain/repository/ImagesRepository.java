package ecommerce.domain.repository;



import ecommerce.domain.entities.Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagesRepository  extends JpaRepository<Images,Integer> {
    //List<Images> findAllByProduct();
}
