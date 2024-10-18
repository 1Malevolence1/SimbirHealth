package com.example.Timetable_microservice.timetable.service.utils;

public interface MicroserviceEntityChecker {

    void checkEntityForHospital(Long id, String room, String token);
    void checkEntityForHospital(Long id, String token);
    void checkEntityTimetable(Long id);
    void checkEntityForDoctor(Long id, String token);
}
