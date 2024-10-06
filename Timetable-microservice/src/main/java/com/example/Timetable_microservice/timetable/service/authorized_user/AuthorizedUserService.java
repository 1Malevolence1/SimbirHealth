package com.example.Timetable_microservice.timetable.service.authorized_user;

import com.example.Timetable_microservice.timetable.dto.ResponseTimetableDto;

import java.time.LocalDateTime;
import java.util.List;

public interface AuthorizedUserService {

    List<ResponseTimetableDto> getAllTimetable(LocalDateTime from, LocalDateTime to, Long id);
}
