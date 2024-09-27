package com.example.Account_microservice.security.jwt.dto;

public record JwtRefreshTokeRequest(
        String refreshToken
) {
}
