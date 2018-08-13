package com.example.user.controller;

import com.example.user.exception.ResourceNotFoundException;
import com.example.user.model.User;
import com.example.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserRepository UserRepository;

    // Get All Users
    @GetMapping("/user")
    public List<User> getAllUsers() {
        return UserRepository.findAll();
    }

    //Login
//    @GetMapping("/Login")
//    public User login(@Valid @RequestBody User User)
//    {
//        User.getUsername();
//        User.getPassword();
//
//    }
    // Get a Single User
    @PostMapping("/login")
    public String login(@Valid @RequestBody User User) {
        User Login= UserRepository.findByUsername(User.getUsername(),User.getPassword());
        if(Login!=null)
        {
            if(Login.getType().equals("sharing"))
            {
                return "Welcome! Home User";
            }
            else
            {
                return "Welcome! Corporate User";
            }
        }
        return "Login Failed";
    }

    // Create a new User
    @PostMapping("/user")
    public User createUser(@Valid @RequestBody User User) {
        return UserRepository.save(User);
    }

    // Get a Single User
//    @GetMapping("/user/{id}")
//    public User getUserById(@PathVariable(value = "id") Long UserId) {
//        return UserRepository.findById(UserId)
//                .orElseThrow(() -> new ResourceNotFoundException("User", "id", UserId));
//    }
    // Update a User
    @PutMapping("/user/{id}")
    public User updateUser(@PathVariable(value = "id") Long UserId,
                           @Valid @RequestBody User UserDetails) {

        User User = UserRepository.findById(UserId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", UserId));

        User.setUsername(UserDetails.getUsername());
        User.setPassword(UserDetails.getPassword());
        User.setType(UserDetails.getType());

        User updatedUser = UserRepository.save(User);
        return updatedUser;
    }

    // Delete a User
    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long UserId) {
        User User = UserRepository.findById(UserId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", UserId));

        UserRepository.delete(User);

        return ResponseEntity.ok().build();
    }
}
