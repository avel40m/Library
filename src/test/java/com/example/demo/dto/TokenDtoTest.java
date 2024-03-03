package com.example.demo.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TokenDtoTest {
    @Test
    void tokenConstructorAndGetting(){
        var token = "token";
        var tokenResponse = new TokenResponse(token);
        var tokenResponseTwo = new TokenResponse(token);

        var hashCodeOne = tokenResponse.hashCode();
        var hashCodeTwo = tokenResponseTwo.hashCode();
        assertEquals(token, tokenResponse.getJwtToken());
        assertEquals("TokenResponse(jwtToken=token)",tokenResponse.toString());
        assertEquals(hashCodeOne, hashCodeTwo);
        assertTrue(tokenResponse.equals(tokenResponse));
        assertFalse(tokenResponseTwo.equals(null));
        assertFalse(tokenResponseTwo.equals(""));
        assertTrue(tokenResponse.equals(tokenResponseTwo));
    }

    @Test
    void tokenConstructorAndSetting(){
        var tokenResponse = new TokenResponse();
        var token = "token";
        tokenResponse.setJwtToken(token);
        assertEquals(token, tokenResponse.getJwtToken());
    }
}
