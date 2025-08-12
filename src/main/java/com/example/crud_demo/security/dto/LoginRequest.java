package com.example.crud_demo.security.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}