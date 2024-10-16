package com.example.Timetable_microservice.appointment.service;

import com.example.Timetable_microservice.appointment.model.Appointment;
import com.example.Timetable_microservice.timetable.model.Timetable;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentSlotGenerator {

    List<Appointment> generate(LocalDateTime from, LocalDateTime to, Timetable timetable);
}
