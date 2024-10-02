package com.example.Account_microservice.user.dto;

public record RequestSingInUserAccountDto(
        String username,
        String password
) {
}
