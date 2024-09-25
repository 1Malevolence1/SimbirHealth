package com.example.Account_microservice.user.dto;

public record ResponseUserDto (
        Long id,
        String lastname,
        String firsName,
        String username,
        String password
) {
}
