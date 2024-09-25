package com.example.Account_microservice.user.dto;

public record RequestSingInUserDto(
        String username,
        String password
) {
}
