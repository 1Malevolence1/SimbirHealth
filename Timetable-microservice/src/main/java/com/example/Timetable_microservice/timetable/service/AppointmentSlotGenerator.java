package com.example.Timetable_microservice.timetable.service;

import com.example.Timetable_microservice.appointment.dto.appointment.AppointmentDto;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentSlotGenerator {

    List<AppointmentDto> generate(LocalDateTime from, LocalDateTime to);
}
