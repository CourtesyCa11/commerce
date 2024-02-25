package ecommerce.dto;


import ecommerce.domain.entities.Order;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    @Valid

    @NotBlank
    private Integer orderId;

    private Integer userId;

    @NotBlank
    private List<ProductDto> products;
    @NotBlank
    @PastOrPresent
    private Date date;

    @NotBlank
    @Size(min=1)
    private double price;


    public OrderDto(Order order) {
        this.orderId = order.getOrderId();
        this.date = order.getDate();
        this.price = order.getPrice();
    }
}
