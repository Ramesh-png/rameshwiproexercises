package com.wipro.userservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wipro.userservice.dto.Token;
import com.wipro.userservice.dto.UserDTO;
import com.wipro.userservice.entity.User;
import com.wipro.userservice.service.UserService;
import com.wipro.userservice.util.AppConstant;

import io.jsonwebtoken.Jwts;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
@Tag(name = "User Management", description = "APIs for managing users")
public class UserController {

    @Autowired
    private UserService userService;

    // Register new user
    @PostMapping("/register")
    @Operation(summary = "Register a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User registered successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<User> registerUser(@Valid @RequestBody UserDTO userDTO) {
        User user = userService.registerUser(userDTO);
        return ResponseEntity.ok(user);
    }

    // User login
    @PostMapping("/login")
    @Operation(summary = "User login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login successful"),
            @ApiResponse(responseCode = "401", description = "Invalid credentials")
    })
    public ResponseEntity<Token> login(@Valid @RequestBody UserDTO userDTO) {
        Token token = userService.login(userDTO); // service returns JWT token
        return ResponseEntity.ok(token);
    }

    // Update user
    @PutMapping("/{id}")
    @Operation(summary = "Update user details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody UserDTO userDTO) {
        User user = userService.updateUser(id, userDTO);
        return ResponseEntity.ok(user);
    }

    // Delete user
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    // Get all users
    @GetMapping
    @Operation(summary = "Get all users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // Get user by ID
    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    // Get user by username
    @GetMapping("/username/{username}")
    @Operation(summary = "Get user by username")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        User user = userService.getUserByUsername(username);
        return ResponseEntity.ok(user);
    }
    
    

}
