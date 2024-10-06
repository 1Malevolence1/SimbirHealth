package com.example.Timetable_microservice.timetable.service.AdminAndManagerService;


import com.example.Timetable_microservice.timetable.convert.manager.ManagerMapperTimetable;
import com.example.Timetable_microservice.timetable.dto.RequestTimetableDto;
import com.example.Timetable_microservice.timetable.service.TimetableService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminAndManagerServiceImpl implements AdminAndManagerService {

    private final TimetableService timetableService;
    private final ManagerMapperTimetable mapperTimetable;

    @Override
    public void add(RequestTimetableDto dto) {
            timetableService.save(
                    mapperTimetable.toModel(dto)
            );
    }

    @Override
    public void update(RequestTimetableDto dto, Long id) {
        timetableService.update(
                mapperTimetable.toModel(dto, id)
        );
    }

    @Override
    public void deleteById(Long id) {
        timetableService.deleteById(id);
    }
}
