package com.example.Account_microservice.user.dto.admin;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record RequestAdminSaveAccount(
        @NotBlank(message = "lastName не должно быть пусытм")
        String lastName,
        @NotBlank(message = "firstName не должно быть пусытм")
        String firstName,
        @NotBlank(message = "username не должно быть пусытм")
        String username,
        @NotBlank(message = "password не должно быть пусытм")
        String password,

        @NotNull(message = "roles не должно быть пустым. Должна быть хотя бы одна роль")
        Set<String> roles
) {}
