package com.example.crud_demo.security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenResponse {
    private String accessToken;
    private String tokenType;
    private String username;
    private Date issuedAt;
    private Date expiresAt;
    private String issuer;
    private String tokenId;
    private String message;
}