package com.example.Timetable_microservice.timetable.service.utils;

import com.example.Timetable_microservice.timetable.dto.timetable.RequestTimetableDto;
import com.example.Timetable_microservice.timetable.model.Timetable;

public interface TimetablePreparationService {


    Timetable build(RequestTimetableDto dto);
    Timetable build(RequestTimetableDto dto, Long timetableId);
}
