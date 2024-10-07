package com.example.Timetable_microservice.appointment.service;


import com.example.Timetable_microservice.appointment.model.Appointment;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentService {

    List<Appointment> fetchAvailableSlots(Long id);
    void updateActiveOnTrue(LocalDateTime time, Long userId);
    void updateActiveOnFalse(LocalDateTime time, Long userId);
}
