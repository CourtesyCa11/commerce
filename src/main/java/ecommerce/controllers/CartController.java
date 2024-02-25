package ecommerce.controllers;



import ecommerce.dto.CartDto;
import ecommerce.service.CartInterface;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/cart")
public class CartController {

    @Autowired
    CartInterface service;



    @GetMapping(value = {"/get/{userId}"})
    public ResponseEntity<CartDto> getCart(@Valid @PathVariable Long userId) {
        return service.getCart(userId);
    }

    @PostMapping(value = {"/add"})
    public ResponseEntity<String> addCart(@Valid @RequestBody CartDto cartDto) {
        return service.addCart(cartDto);
    }

    @DeleteMapping(value = {"/delete/{userId}"})
    public ResponseEntity<String> deleteCart(@Valid @PathVariable Long userId) {
        return service.deleteCart(userId);


    }

    @PutMapping(value = {"/update"})
    public ResponseEntity<String> updateCart(@Valid @RequestBody CartDto cartDto, Errors errors) {
            return  service.updateCart(cartDto);
    }
}
