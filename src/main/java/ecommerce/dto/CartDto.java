package ecommerce.dto;


import ecommerce.domain.entities.Cart;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
    @Valid

    @NotBlank(message = "User id is required")
    Long CartId;
    @NotBlank(message = "At least one order is required")
    ArrayList<OrderDto> orders;
    @NotBlank(message = "Date  is required")
    @PastOrPresent
    Date date;

    public CartDto(Cart e) {
        this.CartId = e.getCartId();
        this.orders = getOrders();
        this.date = getDate();
    }
}
