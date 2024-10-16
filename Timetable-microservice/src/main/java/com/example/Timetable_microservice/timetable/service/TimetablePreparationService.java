package com.example.Timetable_microservice.timetable.service;

import com.example.Timetable_microservice.timetable.dto.RequestTimetableDto;
import com.example.Timetable_microservice.timetable.model.Timetable;

public interface TimetablePreparationService {


    Timetable build(RequestTimetableDto dto);
    Timetable build(RequestTimetableDto dto, Long timetableId);
}
