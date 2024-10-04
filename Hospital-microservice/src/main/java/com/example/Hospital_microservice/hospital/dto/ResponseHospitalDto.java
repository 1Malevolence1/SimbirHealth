package com.example.Hospital_microservice.hospital.dto;

import java.util.List;

public record ResponseHospitalDto(

        String name,

        String address,

        String contactPhone,

        List<ResponseRoomsDto> rooms
) {
}
