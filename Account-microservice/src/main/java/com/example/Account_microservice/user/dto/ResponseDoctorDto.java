package com.example.Account_microservice.user.dto;

public record ResponseDoctorDto(
        Long id,
        String lastName,
        String firstName,
        String username
)  {
}
