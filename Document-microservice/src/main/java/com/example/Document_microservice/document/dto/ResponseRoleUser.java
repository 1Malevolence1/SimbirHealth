package com.example.Document_microservice.document.dto;

import java.util.List;

public record ResponseRoleUser(
        List<String> roles
) {
}
