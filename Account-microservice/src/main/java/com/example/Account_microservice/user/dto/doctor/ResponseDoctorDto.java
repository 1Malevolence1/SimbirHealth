package com.example.Account_microservice.user.dto.doctor;

public record ResponseDoctorDto(
        Long id,
        String lastName,
        String firstName,
        String username
)  {
}
