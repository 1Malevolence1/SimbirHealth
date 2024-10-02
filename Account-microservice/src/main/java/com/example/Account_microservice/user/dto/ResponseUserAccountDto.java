package com.example.Account_microservice.user.dto;

public record ResponseUserAccountDto(
        Long id,
        String lastName,
        String firstName,
        String username
) {
}
