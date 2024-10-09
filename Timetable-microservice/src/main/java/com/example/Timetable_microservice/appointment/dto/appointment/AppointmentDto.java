package com.example.Timetable_microservice.appointment.dto.appointment;

import java.time.LocalDateTime;

public record AppointmentDto(

         LocalDateTime recording,
         Boolean active
) {
}
