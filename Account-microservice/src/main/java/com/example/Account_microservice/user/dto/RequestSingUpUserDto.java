package com.example.Account_microservice.user.dto;

import com.example.Account_microservice.user.model.Role;

import java.util.List;

public record RequestSingUpUserDto(
        String lastName,
        String firstName,
        String username,
        String password,
        List<String> roles) {
}
