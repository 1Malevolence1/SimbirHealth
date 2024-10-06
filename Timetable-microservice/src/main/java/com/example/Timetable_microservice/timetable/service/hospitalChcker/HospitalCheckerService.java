package com.example.Timetable_microservice.timetable.service.hospitalChcker;


import com.example.Timetable_microservice.timetable.dto.hopsital.ResponseCheckHospitalDto;

public interface HospitalCheckerService {

    ResponseCheckHospitalDto find(Long id, String token);
    void checkRoomForHospital(String room, ResponseCheckHospitalDto dto, Long id);
}
