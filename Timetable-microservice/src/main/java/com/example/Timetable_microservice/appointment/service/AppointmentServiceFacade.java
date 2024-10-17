package com.example.Timetable_microservice.appointment.service;

import com.example.Timetable_microservice.appointment.dto.appointment.ResponseAppointmentsDto;
import com.example.Timetable_microservice.appointment.model.Appointment;
import com.example.Timetable_microservice.timetable.model.Timetable;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentServiceFacade {

    List<Appointment> generateAppointments(LocalDateTime from, LocalDateTime to, Timetable timetable);
    List<ResponseAppointmentsDto> getAllAvailableSlots(Long id);
    void makeAppointment(LocalDateTime time, Long timetableId, Long userId);
    void cancelAppointment(Long userId, Long appointmentId);
}
