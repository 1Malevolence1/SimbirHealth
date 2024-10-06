package com.example.Timetable_microservice.timetable.convert.manager;

import com.example.Timetable_microservice.timetable.convert.mapper.MapperTimetable;
import com.example.Timetable_microservice.timetable.dto.RequestTimetableDto;
import com.example.Timetable_microservice.timetable.model.Timetable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class ManagerMapperTimetableImpl implements ManagerMapperTimetable {

    private final MapperTimetable mapperTimetable;
    @Override
    public Timetable toModel(RequestTimetableDto dto) {
        return mapperTimetable.toModel(dto);
    }

    @Override
    public Timetable toModel(RequestTimetableDto dto, Long id) {
        return mapperTimetable.toModel(dto, id);
    }
}
