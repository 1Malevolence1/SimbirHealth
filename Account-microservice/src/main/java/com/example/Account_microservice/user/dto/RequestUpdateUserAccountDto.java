package com.example.Account_microservice.user.dto;

import jakarta.validation.constraints.NotBlank;



public record RequestUpdateUserAccountDto(
        @NotBlank(message = "lastName не должно быть пусытм")
        String lastName,
        @NotBlank(message = "firstName не должно быть пусытм")
        String firstName,
        @NotBlank(message = "password не должно быть пусытм")
        String password)
      {
}
