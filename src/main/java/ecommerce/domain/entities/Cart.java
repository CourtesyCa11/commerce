package ecommerce.domain.entities;


import ecommerce.dto.CartDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_id_seq")
    private Long cartId;

    private Date date;

   // @OneToMany
  //  private List<Product> product;


    public Cart(CartDto cartDto) {
        this.cartId = cartDto.getCartId();
        this.date = cartDto.getDate();
    }
}
