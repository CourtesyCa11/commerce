package ecommerce.domain.entities;

import ecommerce.dto.CategoryDto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "categories")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    String category;


    public Category(CategoryDto categoryDto) {
        this.category = categoryDto.getCategory();
    }
}
