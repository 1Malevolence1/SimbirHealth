package com.example.Document_microservice.dto;

import java.util.List;

public record ResponseRoomsHospitalDto(
        List<ResponseRoom> rooms
) {
}
