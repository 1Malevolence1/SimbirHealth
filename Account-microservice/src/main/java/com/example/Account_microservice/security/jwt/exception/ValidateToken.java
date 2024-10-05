package com.example.Account_microservice.security.jwt.exception;

public record ValidateToken(
        String errors,
        String token
) {
}
