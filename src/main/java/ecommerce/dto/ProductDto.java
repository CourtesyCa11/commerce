package ecommerce.dto;


import ecommerce.domain.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Integer productId;
    private String category;
    private String name;
    //private List<String> images;
    private Double actualPrice;
    private Integer sale;
    private Boolean isNew;
    private short rating;
    private String color;
    private Integer amount;
    private Date dateSale;


    public ProductDto(Product product) {
        this.productId = product.getProductId();
        this.category = product.getCategory().getCategory();
        this.name = product.getName();
        this.actualPrice = product.getActualPrice();
        this.sale = product.getSale();
        this.isNew = product.getIsNew();
        this.rating = product.getRating();
        this.color = product.getColor();
        this.amount = product.getAmount();
        this.dateSale = product.getDateSale();
    }
}
