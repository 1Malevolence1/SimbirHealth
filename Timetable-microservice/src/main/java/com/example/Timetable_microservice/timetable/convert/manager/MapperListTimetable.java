package com.example.Timetable_microservice.timetable.convert.manager;


import com.example.Timetable_microservice.timetable.convert.mapper.MapperTimetable;
import com.example.Timetable_microservice.timetable.dto.ResponseTimetableDto;
import com.example.Timetable_microservice.timetable.model.Timetable;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = MapperTimetable.class)
public interface MapperListTimetable {

    List<ResponseTimetableDto> toDto(List<Timetable> timetables);
}
