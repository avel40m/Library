package com.example.demo.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AuthRequestDtoTest {
    @Test
    void testConstructorAndGetter(){
        var username = "username";
        var password = "password";

        var authRequest = new AuthRequest(username,password);
        var authRequestTwo = new AuthRequest(username,password);

        var hashCodeOne = authRequest.hashCode();
        var hashCodeTwo = authRequest.hashCode();

        assertEquals(username, authRequest.getUsername());
        assertEquals(password, authRequest.getPassword());
        assertEquals("AuthRequest(username=username, password=password)",authRequest.toString());
        assertEquals(hashCodeOne, hashCodeTwo);
        assertTrue(authRequest.equals(authRequest));
        assertFalse(authRequestTwo.equals(null));
        assertFalse(authRequestTwo.equals(""));
        assertTrue(authRequest.equals(authRequestTwo));
    }

    @Test
    void testConstructorAndSetting(){
        var authRequest = new AuthRequest();
        String username = "testUsername";
        String password = "testPassword";
        authRequest.setUsername(username);
        authRequest.setPassword(password);
        assertEquals(username, authRequest.getUsername());
        assertEquals(password, authRequest.getPassword());
    }
}
