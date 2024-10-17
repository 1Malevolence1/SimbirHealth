package com.example.Document_microservice.document.dto;

import java.util.List;

public record ResponseRoomsHospitalDto(
        List<ResponseRoom> rooms
) {
}
