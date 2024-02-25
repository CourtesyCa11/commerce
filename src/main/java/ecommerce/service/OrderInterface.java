package ecommerce.service;

import ecommerce.dto.OrderDto;

import org.springframework.http.ResponseEntity;

public interface OrderInterface {

    ResponseEntity<OrderDto> getOrder(Integer orderId);
    ResponseEntity addOrder(OrderDto orderDto);
    ResponseEntity deleteOrder(Integer orderId);
    ResponseEntity updateOrder(OrderDto orderDto);

}


