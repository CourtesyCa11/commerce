package ecommerce.domain.entities;

import ecommerce.dto.OrderDto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="orders")
public class Order {

    @Id
    @Column(name = "uuid")
    private Integer orderId;
    //When order was created
    private Date date;

    @ManyToMany
    @JoinTable(name = "order_product",
            joinColumns = @JoinColumn(name = "productId"),
            inverseJoinColumns = @JoinColumn(name = "orderId")
    )
   private List<Product> products;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    private double price;

    public Order(OrderDto orderDto, List<Product> products) {
        this.orderId = orderDto.getOrderId();
        this.date = orderDto.getDate();
        this.price = orderDto.getPrice();
        this.products = products;
    }


//    public Order(OrderDto orderDto) {
//        this.orderId = orderDto.getOrderId();
//        this.date = orderDto.getDate();
//        this.price = orderDto.getPrice();
//        this.products = orderDto.getProducts().stream().map(productDto -> new Product(productDto)).toList();
//
//    }
}
