package com.example.Account_microservice.user.dto;

public record ResponseAccountDto (
        Long id,
        String lastname,
        String firsName,
        String username
) {
}
