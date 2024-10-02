package com.example.Account_microservice.user.dto;

import jakarta.validation.constraints.NotBlank;

public record RequestSingUpUserAccountDto(
        @NotBlank(message = "lastName не должно быть пусытм")
        String lastName,
        @NotBlank(message = "firstName не должно быть пусытм")
        String firstName,
        @NotBlank(message = "username не должно быть пусытм")
        String username,
        @NotBlank(message = "password не должно быть пусытм")
        String password)
      {
}
