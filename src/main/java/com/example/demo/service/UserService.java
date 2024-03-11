package com.example.demo.service;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.TokenResponse;
import com.example.demo.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    ResponseEntity<TokenResponse> authentication(AuthRequest request);
    ResponseEntity<User> register(User request);
}
