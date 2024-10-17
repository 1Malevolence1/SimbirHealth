package com.example.Document_microservice.security.jwt.dto;

import java.util.List;

public record ResponseDataUserDto(
        String username,
        List<String> roles
) {
}
