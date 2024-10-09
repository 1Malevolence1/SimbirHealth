package com.example.Timetable_microservice.timetable.convert.manager;

import com.example.Timetable_microservice.timetable.dto.RequestTimetableDto;
import com.example.Timetable_microservice.timetable.dto.ResponseTimetableDto;
import com.example.Timetable_microservice.timetable.model.Timetable;

import java.util.List;

public interface ManagerMapperTimetable {

    Timetable toModel(RequestTimetableDto dto);
    Timetable toModel(RequestTimetableDto dto, Long id);

    List<ResponseTimetableDto> toDto(List<Timetable> listModel);
}
