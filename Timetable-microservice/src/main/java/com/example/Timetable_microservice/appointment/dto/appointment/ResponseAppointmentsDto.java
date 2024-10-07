package com.example.Timetable_microservice.appointment.dto.appointment;

import java.time.LocalDateTime;

public record ResponseAppointmentsDto(
        LocalDateTime recording) {
}
