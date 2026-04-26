package com.shrey.food_management_system.food_management_system.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JWTutil {

    private static final String SECRET = "my_super_secret_key_123456789_my_app_my_super_secret_key_123456789";

    private static final Key key =
            Keys.hmacShaKeyFor(SECRET.getBytes());

    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 10;

    // Generate Token
    public static String generateToken(String email, String role) {

        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    // Extract Claims (FIXED WAY)
    private static Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Extract Email
    public static String extractEmail(String token) {
        return extractAllClaims(token).getSubject();
    }

    // Validate Token
    public static boolean validateToken(String token) {
        try {
            extractAllClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public static String extractRole(String token) {
        return extractAllClaims(token).get("role", String.class);
    }
}