package ecommerce.service;

import ecommerce.dto.UserDto;

import org.springframework.http.ResponseEntity;

public interface UserInterface {

    ResponseEntity<String> addAccount(UserDto user);
    ResponseEntity<String> removeAccount(Integer userId);
    ResponseEntity<UserDto> getUser(Integer userId);
    ResponseEntity<String> updatePassword(Integer id, String password);
    ResponseEntity<String> revokeAccount(Integer id);
}
