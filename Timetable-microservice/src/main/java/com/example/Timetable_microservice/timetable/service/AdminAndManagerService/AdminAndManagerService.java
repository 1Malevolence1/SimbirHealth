package com.example.Timetable_microservice.timetable.service.AdminAndManagerService;

import com.example.Timetable_microservice.timetable.dto.RequestTimetableDto;

public interface AdminAndManagerService {

    void add(RequestTimetableDto dto);

    void update(RequestTimetableDto dto, Long id);

    void deleteById(Long id);

    void deleteAllByDoctorId(Long id);
}
