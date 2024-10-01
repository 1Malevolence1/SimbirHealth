package com.example.Account_microservice.user.dto;

public record ResponseAccountDto (
        Long id,
        String lastName,
        String firstName,
        String username
) {
}
