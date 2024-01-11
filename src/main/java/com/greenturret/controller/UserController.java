package com.greenturret.controller;

import com.greenturret.dto.UserDTO;
import com.greenturret.model.User;
import com.greenturret.response.ApiResponse;
import com.greenturret.response.EResponseStatus;
import com.greenturret.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO){
        // Call UserService to handle the creation of a new user
        userService.createUser(userDTO);
        ApiResponse response = new ApiResponse(EResponseStatus.SUCCESS, "User registered successfully", null);
        return ResponseEntity.ok().body("User Registrated succsufully");
    }

}
