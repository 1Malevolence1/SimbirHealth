package com.example.Hospital_microservice.hospital.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record RequestCreateHospitalDto(

        @NotBlank(message = "поле name не должно быть пустым")
        String name,

        @NotBlank(message = "поле address не должно быть пустым")
        String address,

        @NotBlank(message = "поле contactPhone не должно быть пустым")
        String contactPhone,

        @NotNull(message = "поле rooms должно содержать хотябы одну комнату")
        List<String> rooms
) {
}
