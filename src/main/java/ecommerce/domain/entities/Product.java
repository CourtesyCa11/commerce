package ecommerce.domain.entities;

import ecommerce.dto.ProductDto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @Column(name = "uuid")
    private Integer productId;
    private String name;
  //  @OneToMany(mappedBy = "product")
  //  private List<Images> images;
    private Double actualPrice;
    private Integer sale;
    private Boolean isNew;
    private short rating;
    private String color;
    private Integer amount;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "category",referencedColumnName = "category")
    private Category category;
    private Date dateSale;
    @ManyToMany(mappedBy = "products")
    private List<Order> order;



    public Product(Integer productId, String name, Double actualPrice, Integer sale, Boolean isNew,
                   short rating, String color, Integer amount, Category category, Date dateSale) {
        this.productId = productId;
        this.name = name;
        this.actualPrice = actualPrice;
        this.sale = sale;
        this.isNew = isNew;
        this.rating = rating;
        this.color = color;
        this.amount = amount;
        this.category = category;
        this.dateSale = dateSale;
    }

    public Product(ProductDto productDto, Category category) {
        this.productId = productDto.getProductId();
        this.name = productDto.getName();
        this.actualPrice = productDto.getActualPrice();
        this.sale = productDto.getSale();
        this.isNew = productDto.getIsNew();
        this.rating = productDto.getRating();
        this.color = productDto.getColor();
        this.amount = productDto.getAmount();
        this.category = category;
        this.dateSale = productDto.getDateSale();
    }
}
