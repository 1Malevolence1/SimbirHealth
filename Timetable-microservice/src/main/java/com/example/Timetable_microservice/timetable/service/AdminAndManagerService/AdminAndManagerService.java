package com.example.Timetable_microservice.timetable.service.AdminAndManagerService;

import com.example.Timetable_microservice.timetable.dto.timetable.RequestTimetableDto;

public interface AdminAndManagerService {

    void add(RequestTimetableDto dto);

    void update(RequestTimetableDto dto, Long timetableId);

    void deleteById(Long id);

    void deleteAllByDoctorId(Long id);
    void deleteAllByHospitalId(Long id);

    void cancelAppointment(Long appointmentId);
}
