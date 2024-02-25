package ecommerce.service;

import ecommerce.domain.entities.User;
import ecommerce.domain.repository.UserRepository;
import ecommerce.dto.UserDto;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;


@Service
public class UserImpl implements UserInterface{

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;


    @Override
    public ResponseEntity<String> addAccount(UserDto dto) {
        if(userRepository.existsById(dto.getId()))
            return new ResponseEntity<>("This user id is already exists", HttpStatus.CONFLICT);
        User user = new User(dto,getHashCode(dto.getPassword()), LocalDateTime.now());
        userRepository.save(user);
        return new ResponseEntity<>("User is successfully created",HttpStatus.CREATED);
    }

    private String getHashCode(String password) {
        return encoder.encode(password);
    }

    @Override
    public ResponseEntity<String> removeAccount(Integer userId) {
        if(!userRepository.existsById(userId))
            return new ResponseEntity<>("This user id is not exists", HttpStatus.NOT_FOUND);
        userRepository.deleteById(userId);
        return new ResponseEntity<>("User is successfully removed", HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<UserDto> getUser(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new EntityNotFoundException("This user id is not exists"));
        UserDto dto = new UserDto(user);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> updatePassword(Integer id, String password) {
        User user = userRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("This user id is not exists"));

        if(encoder.matches(password, user.getPassword()))
            return new ResponseEntity<>("Password is wrong", HttpStatus.CONFLICT);

        user.setPassword(getHashCode(password));
        user.setDateActivation(LocalDateTime.now());
        userRepository.save(user);

        return new ResponseEntity<>("User is successfully updated", HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<String> revokeAccount(Integer userName) {
        return null;
    }
}
