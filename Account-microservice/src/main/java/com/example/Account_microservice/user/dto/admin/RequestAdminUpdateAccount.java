package com.example.Account_microservice.user.dto.admin;


import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record RequestAdminUpdateAccount(

        String lastName,

        String firstName,

        String username,

        String password,

        @NotNull(message = "role не должно быть пустым. Должна быть хотя бы одна роль")
        Set<String> roles
) {}
