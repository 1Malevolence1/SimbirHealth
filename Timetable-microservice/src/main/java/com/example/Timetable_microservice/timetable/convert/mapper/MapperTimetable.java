package com.example.Timetable_microservice.timetable.convert.mapper;


import com.example.Timetable_microservice.timetable.dto.RequestTimetableDto;
import com.example.Timetable_microservice.timetable.model.Timetable;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapperTimetable {

    Timetable toModel(RequestTimetableDto dto);
    Timetable toModel(RequestTimetableDto dto, Long id);
}
