package com.example.fitness.controller;

import com.example.fitness.dto.Auth.RegisterRequest;
import com.example.fitness.dto.Auth.UserResponse;
import com.example.fitness.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;


    //    public User register(@RequestBody RegisterRequest registerRequest) {
//        return userService.addUser(registerRequest);
//    }
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody RegisterRequest registerRequest) {
        System.out.println("Authentication");
        return ResponseEntity.ok(userService.register(registerRequest));
    }

}
