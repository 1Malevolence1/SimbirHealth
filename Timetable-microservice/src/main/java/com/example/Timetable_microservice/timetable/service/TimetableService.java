package com.example.Timetable_microservice.timetable.service;

import com.example.Timetable_microservice.timetable.model.Timetable;

import java.time.LocalDateTime;
import java.util.List;

public interface TimetableService {



    Timetable save(Timetable timetable);

    void update(Timetable timetable);

    void deleteById(Long id);

    void deleteAllByDoctorId(Long id);

    void deleteAllByHospitalId(Long id);

    Timetable getTimeTableById(Long id);

    void existsById(Long id);

    List<Timetable> getAllTimetableWithParamsFromAndToByHospitalId(LocalDateTime from, LocalDateTime to, Long id);
    List<Timetable> getAllTimetableWithParamsFromAndToByHospitalIdAndRoom(LocalDateTime from, LocalDateTime to, String room, Long id);
    List<Timetable> getAllTimetableWithParamsFromAndToByDoctorId(LocalDateTime from, LocalDateTime to, Long id);
}
