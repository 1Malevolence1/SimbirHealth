package com.example.Timetable_microservice.timetable.service;

import com.example.Timetable_microservice.timetable.dto.RequestTimetableDto;

public interface MicroserviceEntityChecker {

    void checkEntityForHospital(Long id, String room, String token);
    void checkEntityForHospital(Long id, String token);
    void checkEntityForUser(Long id, String token);
}
