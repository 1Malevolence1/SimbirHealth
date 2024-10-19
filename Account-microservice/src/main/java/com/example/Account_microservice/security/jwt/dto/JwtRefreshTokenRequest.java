package com.example.Account_microservice.security.jwt.dto;

public record JwtRefreshTokenRequest(
        String refreshToken
) {
}
