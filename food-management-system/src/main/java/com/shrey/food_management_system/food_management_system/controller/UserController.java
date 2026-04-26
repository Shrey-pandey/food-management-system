package com.shrey.food_management_system.food_management_system.controller;

import com.shrey.food_management_system.food_management_system.dto.LoginRequest;
import com.shrey.food_management_system.food_management_system.model.User;
import com.shrey.food_management_system.food_management_system.service.UserService;
import org.springframework.web.bind.annotation.*;
import com.shrey.food_management_system.food_management_system.dto.AuthResponse;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // REGISTER USER
    @PostMapping
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    // GET ALL USERS
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // APPROVE USER (Admin action)
    @PutMapping("/{id}/approve")
    public User approveUser(@PathVariable Long id) {
        return userService.approveUser(id);
    }
    @PostMapping("/login")
    public AuthResponse loginUser(@RequestBody LoginRequest request) {
        System.out.println("LOGIN CONTROLLER HIT");
        return userService.loginUser(request.getEmail(), request.getPassword());
    }
}