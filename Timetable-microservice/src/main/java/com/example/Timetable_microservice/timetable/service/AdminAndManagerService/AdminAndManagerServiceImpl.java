package com.example.Timetable_microservice.timetable.service.AdminAndManagerService;


import com.example.Timetable_microservice.timetable.dto.RequestTimetableDto;
import com.example.Timetable_microservice.timetable.service.TimetablePreparationService;
import com.example.Timetable_microservice.timetable.service.TimetableService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminAndManagerServiceImpl implements AdminAndManagerService {

    private final TimetableService timetableService;
    private final TimetablePreparationService timetablePreparationService;

    @Override
    public void add(RequestTimetableDto dto) {
        timetableService.save(
                timetablePreparationService.build(dto)
        );
    }

    @Override
    public void update(RequestTimetableDto dto, Long id) {
        timetableService.update(
                timetablePreparationService.build(
                        dto, id
                )
        );
    }

    @Override
    public void deleteById(Long id) {
        timetableService.deleteById(id);
    }

    @Override
    public void deleteAllByDoctorId(Long id) {
        timetableService.deleteAllByDoctorId(id);
    }

    @Override
    public void deleteAllByHospitalId(Long id) {
        timetableService.deleteAllByHospitalId(id);
    }


}
