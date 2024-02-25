package ecommerce.controllers;


import ecommerce.dto.UserDto;
import ecommerce.service.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController   {

    @Autowired
    UserInterface service;

    @PostMapping(value = {"/user/add"})
    public ResponseEntity<String> addAccount(@RequestBody UserDto user) {
        return service.addAccount(user);
    }

    @DeleteMapping(value = {"/user/delete/{userId}"})
    public ResponseEntity<String> removeAccount(@PathVariable Integer userId) {
        return service.removeAccount(userId);
    }

    @GetMapping(value = {"/user/get/{userId}"})
    public ResponseEntity<UserDto> getUser(@PathVariable Integer userId) {
        return service.getUser(userId);
    }

    @PutMapping(value = {"/user/update/{id}"})
    public ResponseEntity<String> updatePassword(@PathVariable Integer id,@RequestParam String password) {
        return service.updatePassword(id,password);
    }

    @PutMapping
    public ResponseEntity<String> revokeAccount(Integer id) {
        return null;
    }
}
