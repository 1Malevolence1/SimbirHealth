package com.example.Timetable_microservice.appointment.service;


import com.example.Timetable_microservice.appointment.model.Appointment;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentService {

    List<Appointment> fetchAvailableSlots(Long id);
    boolean findUserIdFromAppointment(Long userId);
    Long getCountUserSignedUpForAppointment(Long timetableId);
    Long getUserIdFromAppointment(Long userId);
    void updateActiveOnTrue(LocalDateTime time, Long timetableId, Long userId);
    void updateActiveOnFalse(Long userId, Long appointmentId);
    void updateActiveOnFalse(Long appointmentId);
    void deleteAllAppointmentByIdTimetable(Long timetable);
    void saveAppointments(List<Appointment> appointments);
}
