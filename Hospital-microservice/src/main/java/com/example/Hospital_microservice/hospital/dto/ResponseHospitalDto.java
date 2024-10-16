package com.example.Hospital_microservice.hospital.dto;

import java.util.List;

public record ResponseHospitalDto(
        Long id,

        String name,

        String address,

        String contactPhone,

        List<ResponseHospitalRoomsDto> rooms
) {
}
