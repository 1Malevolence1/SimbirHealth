package com.example.Timetable_microservice.timetable.service.authorized_user;


import com.example.Timetable_microservice.timetable.convert.manager.ManagerMapperTimetable;
import com.example.Timetable_microservice.timetable.dto.ResponseTimetableDto;
import com.example.Timetable_microservice.timetable.service.TimetableService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorizedUserServiceImpl implements AuthorizedUserService {

    private final TimetableService timetableService;
    private final ManagerMapperTimetable mapperTimetable;

    @Override
    public List<ResponseTimetableDto> getAllTimetableByHospitalById(LocalDateTime from, LocalDateTime to, Long id) {
        return mapperTimetable.toDto(
                timetableService.getAllTimetableWithParamsFromAndToByHospitalId(from, to, id)
        );
    }

    @Override
    public List<ResponseTimetableDto> getAllTimetableByDoctorById(LocalDateTime from, LocalDateTime to, Long id) {
        return mapperTimetable.toDto(
                timetableService.getAllTimetableWithParamsFromAndToByDoctorId(from, to, id)
        );
    }
}
