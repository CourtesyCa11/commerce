package ecommerce.service;

import ecommerce.domain.entities.Cart;
import ecommerce.domain.repository.CartRepository;
import ecommerce.dto.CartDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartImpl implements CartInterface{

    @Autowired
    CartRepository repository;

    @Override
    public ResponseEntity<CartDto> getCart(Long userId) {
        Cart cart = repository.findById(userId).orElse(null);
        CartDto dto = new CartDto(cart);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<String> addCart(CartDto cartDto) {
        if(repository.existsById(cartDto.getCartId()))
        return new ResponseEntity<>("Already exists",HttpStatus.CONFLICT);
        repository.save(new Cart(cartDto));
        return new ResponseEntity<>("Successfully added",HttpStatus.ACCEPTED);
    }

    @Override
    @Transactional
    public ResponseEntity deleteCart(Long userId) {
        if(!repository.existsById(userId))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        repository.deleteById(userId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @Override
    @Transactional
    public ResponseEntity updateCart(CartDto cartDto) {

        Cart cart = repository.findById(cartDto.getCartId()).orElse(null);
            if(cart == null)
                return ResponseEntity.status(401).body(HttpStatus.NOT_FOUND);
        //cart.setOrders(cartDto.getOrders());
        return ResponseEntity.status(201).body(HttpStatus.ACCEPTED);
    }

//    @Override
//    public List<CartDto> getCartByDate(LocalDate from, LocalDate to) {
//
//        repository.findByDateBetween(from,to);
//
//        return null;
//    }


    @Override
    public List<CartDto> getCarts() {
        List<Cart> carts = repository.findAll();
        List<CartDto> dtos = swapToDto(carts);
        return dtos;
    }

    @Override
    public CartDto getCartOrder(Long orderId) {
        return null;
    }

    private List<Cart> swapToEntitiesCarts(List<CartDto> carts){
        List<Cart> entity = carts.stream().map(e -> new Cart(e)).toList();
        return entity;
    }
    private List<CartDto> swapToDto(List<Cart> carts){
        List<CartDto> dtos = carts.stream().map(e -> new CartDto(e)).toList();
        return dtos;
    }
}
