package com.example.Account_microservice.security.jwt.dto;

import java.util.List;

public record ResponseDataUserDto(
        String username,
        List<String> roles
) {
}
