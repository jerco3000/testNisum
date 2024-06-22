package com.prueba.nisum.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.ExpiredJwtException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class JwtUtilTest {

    private JwtUtil jwtUtil;
    private final String secretKey = "my-secret-key";

    @BeforeEach
    public void setUp() {
        jwtUtil = new JwtUtil();
        jwtUtil.setSecret(secretKey);
    }

    @Test
    public void generateTokenTest() {
        String email = "test@gmail.com";
        String token = jwtUtil.generateToken(email);

        assertNotNull(token);

        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();

        assertEquals(email, claims.getSubject());
        assertTrue(claims.getExpiration().after(new Date()));
    }

    @Test
    public void validateTokenTest() {
        String email = "test@gmail.com";
        String token = jwtUtil.generateToken(email);

        assertTrue(jwtUtil.validateToken(token, email));
    }

    @Test
    public void getUsernameFromTokenTest() {
        String email = "test@gmail.com";
        String token = jwtUtil.generateToken(email);

        String extractedEmail = jwtUtil.getUsernameFromToken(token);
        assertEquals(email, extractedEmail);
    }

    @Test
    public void isTokenExpiredTest() {
        String email = "test@gmail.com";
        String token = Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24)) // 24 hours ago
                .setExpiration(new Date(System.currentTimeMillis() - 1000 * 60 * 60)) // 1 hour ago
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        try {
            jwtUtil.validateToken(token, email);
            fail("ExpiredJwtException be thrown");
        } catch (ExpiredJwtException e) {
            assertNotNull(e);
        }
    }
}
