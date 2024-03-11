package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.model.User;
import com.example.demo.service.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuthenticationControllerTest {
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private UserDetailsService userDetailsService;
    @Mock
    private JwtService jwtService;
    @InjectMocks
    private AuthenticationController tokenController;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        authenticationManager = mock(AuthenticationManager.class);
        userDetailsService = mock(UserDetailsService.class);
        jwtService = mock(JwtService.class);
    }

    @Test
    void authenticateTest(){
        var auth = new User();
        var userDetails = mock(UserDetails.class);
        var authRequest = new AuthRequest();
        var token = "token";
        when(jwtService.generateToken(auth)).thenReturn(token);
        when(userDetailsService.loadUserByUsername("username")).thenReturn(userDetails);
        var response = tokenController.authenticate(authRequest);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCode().value());
        assertEquals(token, response.getBody());
    }
}
