package com.example.Account_microservice.user.dto;

import jakarta.validation.constraints.NotBlank;



public record RequestUpdateAccountDto(
        @NotBlank(message = "lastName не должно быть пусытм")
        String lastName,
        @NotBlank(message = "firstName не должно быть пусытм")
        String firstName,
        @NotBlank(message = "password не должно быть пусытм")
        String password)
      {
}
