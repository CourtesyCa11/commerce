package ecommerce.service;


import ecommerce.dto.CartDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CartInterface {

    ResponseEntity<CartDto> getCart(Long userId);
    ResponseEntity addCart(CartDto cartDto);
    ResponseEntity deleteCart(Long userId);
    ResponseEntity updateCart(CartDto cartDto);

   // List<CartDto> getCartByDate(LocalDate from, LocalDate to);

    List<CartDto> getCarts();

    CartDto getCartOrder(Long orderId);

}
