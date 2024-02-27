package ecommerce.service;

import ecommerce.domain.entities.Category;
import ecommerce.domain.entities.Order;
import ecommerce.domain.entities.Product;
import ecommerce.domain.entities.User;
import ecommerce.domain.repository.CategoryRepository;
import ecommerce.domain.repository.OrderRepository;
import ecommerce.domain.repository.ProductRepository;
import ecommerce.domain.repository.UserRepository;

import ecommerce.dto.OrderDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;


@Service
public class OrderImpl implements OrderInterface{


    OrderRepository orderRepository;
    UserRepository userRepository;
    CategoryRepository categoryRepository;
    ProductRepository productRepository;

    @Autowired
    public OrderImpl(OrderRepository orderRepository, UserRepository userRepository, CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<OrderDto> getOrder(Integer orderId) {
        return new ResponseEntity<>(new OrderDto(orderRepository.findById(orderId).orElseThrow( ()-> new EntityNotFoundException("Product is not found"))) , HttpStatus.ACCEPTED);
    }

    @Override
    @Transactional
    public ResponseEntity<String> addOrder(OrderDto orderDto) {
        if(orderRepository.existsById(orderDto.getOrderId()))
            return new ResponseEntity<>("Order is already exists", HttpStatus.CONFLICT);

        User user = userRepository.findById(orderDto.getUserId()).orElseThrow(() -> new EntityNotFoundException("User is not found"));
        List<Product> products = orderDto.getProducts().stream().map(productDto ->{
            Category category = categoryRepository.findById(productDto.getCategory()).orElseThrow(() -> new EntityNotFoundException("Category is not found"));
            Product product = new Product(productDto,category);
            Product entity = productRepository.findById(productDto.getProductId()).orElseThrow(() -> new EntityNotFoundException("Product is not found"));
            entity.setAmount(entity.getAmount()-productDto.getAmount());
            return product;
        } ).toList();
        Order order = new Order(orderDto,products);
        if (user.getOrders().isEmpty())
            user.setOrders(new ArrayList<>(List.of(order)));
        else {
            List<Order> orders = new ArrayList<>(List.copyOf(user.getOrders()));
            orders.add(order);
            user.setOrders(orders);
        }
        orderRepository.save(order);
        return new ResponseEntity<>("Order successfully added", HttpStatus.ACCEPTED);
    }

    @Override
    @Transactional
    public ResponseEntity<String> deleteOrder(Integer orderId) {
        if (!orderRepository.existsById(orderId))
            return new ResponseEntity<>("Order is not found", HttpStatus.NOT_FOUND);
        orderRepository.deleteById(orderId);
        return new ResponseEntity<>("Order successfully removed", HttpStatus.ACCEPTED);
    }

    @Override
    @Transactional
    public ResponseEntity<String> updateOrder(OrderDto orderDto) {
        if (!orderRepository.existsById(orderDto.getOrderId()))
            return new ResponseEntity<>("Order is not found", HttpStatus.NOT_FOUND);
        Order order = orderRepository.findById(orderDto.getOrderId()).orElseThrow(() -> new EntityNotFoundException("Order is not found"));
        order.setProducts(orderDto.getProducts().stream().map(productDto ->{
           Category category = categoryRepository.findById(productDto.getCategory()).orElseThrow(() -> new EntityNotFoundException("Category is not found"));
           return new Product(productDto,category);
        } ).toList());
        order.setPrice(orderDto.getPrice());
        return new ResponseEntity<>("Order is successfully updated", HttpStatus.OK);
    }


//    private toOrder(OrderDto dto){
//
//    }

}
