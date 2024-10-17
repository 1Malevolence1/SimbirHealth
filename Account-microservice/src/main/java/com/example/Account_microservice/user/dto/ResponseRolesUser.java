package com.example.Account_microservice.user.dto;

import java.util.List;

public record ResponseRolesUser(
        List<String> roles
) {
}
