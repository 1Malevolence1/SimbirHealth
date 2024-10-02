package com.example.Account_microservice.user.dto;

import jakarta.validation.constraints.NotBlank;



public record RequestUpdateUserAccountDto(

        String lastName,

        String firstName,

        String password)
      {
}
