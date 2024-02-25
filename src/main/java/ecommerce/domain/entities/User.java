package ecommerce.domain.entities;


import ecommerce.dto.UserDto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    private Integer id;

    private String name;

    private int birthYear;

    private String email;

    private String password;

    private LocalDateTime dateActivation;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;


    public User(UserDto dto, String password, LocalDateTime date) {
        this.id = dto.getId();
        this.name = dto.getName();
        this.birthYear = dto.getBirthYear();
        this.email = dto.getEmail();
        this.password = password;
        this.dateActivation = date;
    }
}

