package com.example.dodast.Security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import com.example.dodast.Exception.InvalidJwtException;
import com.example.dodast.Model.Enums.Role;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


@Service
public class JwtService {
    
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    public String generateToken(long userId, String username, Role role){
        return Jwts.builder().subject(username).claim("userId", userId).claim("role", role.name()).issuedAt(new Date()).expiration(new Date(System.currentTimeMillis() + expiration)).signWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8))).compact();
    }

    public Claims extractClaims(String token){
        try {
            return Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8))).build().parseSignedClaims(token).getPayload();
        } catch (JwtException | IllegalArgumentException e) {
            throw new InvalidJwtException();
        }
        
    }

    public String extractUsername(String token){
        return extractClaims(token).getSubject();
    }

    public Long extractUserId(String token){
        return extractClaims(token).get("userId", Long.class);
    }

    public String extractRole(String token){
        return extractClaims(token).get("role", String.class);
    }
}
