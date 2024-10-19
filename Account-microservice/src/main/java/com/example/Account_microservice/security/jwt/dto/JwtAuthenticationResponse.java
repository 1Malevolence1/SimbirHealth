package com.example.Account_microservice.security.jwt.dto;

public record JwtAuthenticationResponse(
        String accessToken,
        String refreshToken
) {
}
