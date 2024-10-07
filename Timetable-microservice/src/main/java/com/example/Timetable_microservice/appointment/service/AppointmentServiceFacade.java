package com.example.Timetable_microservice.appointment.service;

import com.example.Timetable_microservice.appointment.dto.appointment.ResponseAppointmentsDto;
import com.example.Timetable_microservice.appointment.model.Appointment;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentServiceFacade {

    List<Appointment> generateAppointments(LocalDateTime from, LocalDateTime to);
    List<ResponseAppointmentsDto> getAllAvailableSlots(Long id);
    void makeAppointment(LocalDateTime time, Long id);
    void cancelAppointment(Long userId, Long appointmentId);
}
