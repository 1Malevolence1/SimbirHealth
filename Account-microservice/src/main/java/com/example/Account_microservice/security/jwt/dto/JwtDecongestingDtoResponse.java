package com.example.Account_microservice.security.jwt.dto;

public record JwtDecongestingDtoResponse(
        Long id,
        String username,
        String role
) {
}
