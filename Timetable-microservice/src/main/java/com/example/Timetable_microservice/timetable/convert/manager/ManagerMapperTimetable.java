package com.example.Timetable_microservice.timetable.convert.manager;

import com.example.Timetable_microservice.timetable.dto.RequestTimetableDto;
import com.example.Timetable_microservice.timetable.model.Timetable;

public interface ManagerMapperTimetable {

    Timetable toModel(RequestTimetableDto dto);
    Timetable toModel(RequestTimetableDto dto, Long id);
}
