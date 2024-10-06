package com.example.Timetable_microservice.timetable.convert.manager;

import com.example.Timetable_microservice.timetable.convert.mapper.MapperTimetable;
import com.example.Timetable_microservice.timetable.dto.RequestTimetableDto;
import com.example.Timetable_microservice.timetable.dto.ResponseTimetableDto;
import com.example.Timetable_microservice.timetable.model.Timetable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class ManagerMapperTimetableImpl implements ManagerMapperTimetable {

    private final MapperTimetable mapperTimetable;
    private final MapperListTimetable mapperListTimetable;
    @Override
    public Timetable toModel(RequestTimetableDto dto) {
        return mapperTimetable.toModel(dto);
    }

    @Override
    public Timetable toModel(RequestTimetableDto dto, Long id) {
        return mapperTimetable.toModel(dto, id);
    }

    @Override
    public List<ResponseTimetableDto> toDto(List<Timetable> listModel) {
        return mapperListTimetable.toDto(listModel);
    }
}
