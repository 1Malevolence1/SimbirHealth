package com.example.Timetable_microservice.timetable.dto.hopsital;


import java.util.List;

public record ResponseCheckHospitalDto(
        List<ResponseHospitalRooms> rooms
)  {
}
