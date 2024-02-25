package ecommerce.dto;



import ecommerce.domain.entities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(of="id")
public class UserDto {

    @NotBlank(message = "Id cannot be empty")
    private int id;
    @NotBlank(message = "Name is required")
    private String name;
//    private String lastName;
//    private String displayName;
    @NotBlank(message = "Birth Date is required")
    @Past(message = "Birth Date is wrong")
    private int birthYear;
    @NotBlank(message = "Email is required")
    @Email
    private String email;
    @NotBlank(message = "Password is required")
    private String password;



    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.birthYear = user.getBirthYear();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }
}
