package com.example.Timetable_microservice.appointment.service;


import com.example.Timetable_microservice.appointment.model.Appointment;
import com.example.Timetable_microservice.timetable.model.Timetable;

import java.util.List;

public interface AppointmentService {

    List<Appointment> fetchAvailableSlots(Long id);
    void updateActiveOnTrue(Long id);
    void updateActiveOnFalse(Long id);
}
