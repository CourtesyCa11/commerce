package ecommerce.controllers;

import ecommerce.dto.OrderDto;

import ecommerce.service.OrderInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class OrderController {

    @Autowired
    OrderInterface service;




    @GetMapping(value = {"/order/get/{orderId}"})
    public ResponseEntity<OrderDto> getOrder(@PathVariable Integer orderId) {
        return service.getOrder(orderId);
    }

    @PostMapping(value = {"/order/add"})
    public ResponseEntity<String> addOrder(@RequestBody OrderDto orderDto) {
        return  service.addOrder(orderDto);
    }

    @DeleteMapping(value = {"/order/delete/{orderId}"})
    public ResponseEntity<String> deleteOrder(@PathVariable Integer orderId) {
        return service.deleteOrder(orderId);
    }

    @PutMapping(value = {"/order/update"})
    public ResponseEntity<String> updateOrder(@RequestBody OrderDto orderDto) {
        return service.updateOrder(orderDto);
    }

//    @PutMapping(value = {"/order/{orderId}/set/{amount}"})
//    public ResponseEntity<String> setAmount(@PathVariable Long orderId,@PathVariable Integer amount) {
//        return service.setAmount(orderId, amount);
//    }
}
