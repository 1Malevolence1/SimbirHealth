package com.example.Timetable_microservice.timetable.service.authorized_user;

import com.example.Timetable_microservice.appointment.dto.appointment.ResponseAppointmentsDto;
import com.example.Timetable_microservice.timetable.dto.ResponseTimetableDto;

import java.time.LocalDateTime;
import java.util.List;

public interface AuthorizedUserService {

    List<ResponseTimetableDto> getAllTimetableByHospitalId(LocalDateTime from, LocalDateTime to, Long id);
    List<ResponseTimetableDto> getAllTimetableByHospitalIdAndByRoom(LocalDateTime from, LocalDateTime to, String room, Long id);
    List<ResponseTimetableDto> getAllTimetableByDoctorById(LocalDateTime from, LocalDateTime to, Long id);
    List<ResponseAppointmentsDto> getAllAvailableSlotsByIdTimetable(Long id);
    void makeAppointment(LocalDateTime time, Long timetableId, Long userId);
    void cancelAppointment(String token, Long appointmentId);
}
