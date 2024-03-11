package com.example.demo.service.implementation;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.TokenResponse;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.JwtService;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class IUserService implements UserService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final JwtService jwtService;
    @Autowired
    private final AuthenticationManager authenticationManager;

    @Override
    public ResponseEntity<TokenResponse> authentication(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword())
        );
        try{
            var user = userRepository.findByUsername(request.getUsername());
            var token = jwtService.generateToken(user.get());
            return ResponseEntity.status(HttpStatus.OK).body(new TokenResponse(token));
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().build();
        }

    }

    @Override
    public ResponseEntity<User> register(User request) {
        var user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userRepository.save(user));
    }
}
