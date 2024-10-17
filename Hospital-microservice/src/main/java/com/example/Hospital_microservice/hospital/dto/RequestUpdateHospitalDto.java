package com.example.Hospital_microservice.hospital.dto;

import java.util.List;



public record RequestUpdateHospitalDto(
        String name,
        String address,
        String contactPhone,
        List<String> rooms
) {}
