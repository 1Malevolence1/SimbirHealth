package com.example.Account_microservice.user.dto;

public record RequestSingInAccountDto(
        String username,
        String password
) {
}
