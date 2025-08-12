package com.example.crud_demo.security.controller;

import com.example.crud_demo.security.dto.LoginRequest;
import com.example.crud_demo.security.dto.TokenResponse;
import com.example.crud_demo.security.model.User;
import com.example.crud_demo.security.repository.UserRepository;
import com.example.crud_demo.security.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository,
                         JwtService jwtService,
                         PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody LoginRequest request) {
        if (userRepository.findByUsername(request.getUsername()) != null) {
            return ResponseEntity.badRequest().body("Username already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);

        TokenResponse tokenResponse = TokenResponse.builder()
                .accessToken(jwtService.generateTokenResponse(user.getUsername()).getAccessToken())
                .tokenType("Bearer")
                .username(user.getUsername())
                .issuedAt(new Date())
                .expiresAt(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
                .issuer("Banking API")
                .tokenId(UUID.randomUUID().toString())
                .message("User registered successfully")
                .build();

        return ResponseEntity.ok(tokenResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername());
        if (user != null && passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            TokenResponse tokenResponse = jwtService.generateTokenResponse(user.getUsername());
            return ResponseEntity.ok(tokenResponse);
        }
        return ResponseEntity.badRequest().body("Invalid credentials");
    }
}