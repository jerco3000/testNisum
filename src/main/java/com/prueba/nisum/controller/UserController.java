package com.prueba.nisum.controller;

import com.prueba.nisum.dto.UserRequest;
import com.prueba.nisum.dto.UserResponse;
import com.prueba.nisum.exception.ResourceNotFoundException;
import com.prueba.nisum.model.User;
import com.prueba.nisum.service.UserService;
import com.prueba.nisum.service.UserResponseFactory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Register a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully registered user"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.registerUser(userRequest));
    }

    @Operation(summary = "Get all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved user(s)"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/get")
    public ResponseEntity<?> getUsers(@RequestBody(required = false) UUID userId) {
        if (userId == null) {
            List<User> users = userService.getAllUsers();
            List<UserResponse> userResponses = users.stream()
                    .map(user -> new UserResponseFactory().create(user))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(userResponses);
        } else {
            Optional<User> user = userService.getUserById(userId);
            if (user.isPresent()) {
                UserResponse userResponse = new UserResponseFactory().create(user.get());
                return ResponseEntity.ok(userResponse);
            } else {
                throw new ResourceNotFoundException("User not found");
            }
        }
    }


    @Operation(summary = "Get user by token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved user"),
            @ApiResponse(responseCode = "400", description = "Invalid token"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/get-by-token")
    public ResponseEntity<?> getUserByToken(@RequestBody String token) {
        Optional<UserResponse> userResponse = userService.getUserByToken(token);
        if (userResponse.isPresent()) {
            return ResponseEntity.ok(userResponse.get());
        } else {
            throw new ResourceNotFoundException("User not found");
        }
    }
}
