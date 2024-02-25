package ecommerce.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "images")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Images {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "image_id_seq")
    Integer id;
    String Image;

    @ManyToOne
    Product product;

    public Images(Integer id, String s) {
        this.Image = s;
        this.id = id;
    }

    public Images(String s) {
        this.Image = s;
    }
}
