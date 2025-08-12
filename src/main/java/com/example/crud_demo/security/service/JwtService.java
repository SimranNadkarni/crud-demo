package com.example.crud_demo.security.service;

import com.example.crud_demo.security.dto.TokenResponse;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;
import java.util.UUID;

@Service
public class JwtService {
    private static final String SECRET_KEY = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";
    private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000; // 24 hours

    private Key getSigningKey() {
        byte[] keyBytes = java.util.Base64.getDecoder().decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public TokenResponse generateTokenResponse(String username) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + EXPIRE_DURATION);
        String tokenId = UUID.randomUUID().toString();

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuer("Banking API")
                .setIssuedAt(now)
                .setExpiration(expiration)
                .setId(tokenId)
                .signWith(getSigningKey())
                .compact();

        return TokenResponse.builder()
                .accessToken(token)
                .tokenType("Bearer")
                .username(username)
                .issuedAt(now)
                .expiresAt(expiration)
                .issuer("Banking API")
                .tokenId(tokenId)
                .build();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}