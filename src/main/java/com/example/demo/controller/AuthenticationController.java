package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.TokenResponse;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> authenticate(@RequestBody AuthRequest request){
        return userService.authentication(request);
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User request){
        return userService.register(request);
    }

}
