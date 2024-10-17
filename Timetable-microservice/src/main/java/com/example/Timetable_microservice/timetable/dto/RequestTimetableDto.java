package com.example.Timetable_microservice.timetable.dto;

import com.example.Timetable_microservice.annotation.TimetableValidate;
import com.example.Timetable_microservice.annotation.ValidTimetable;

import java.time.LocalDateTime;

/**
 * DTO for {@link com.example.Timetable_microservice.timetable.model.Timetable}
 */

@ValidTimetable
public record RequestTimetableDto(
        Long doctorId, LocalDateTime from, LocalDateTime to, String room, Long hospitalId)  implements TimetableValidate {

    @Override
    public LocalDateTime getFrom() {
        return from;
    }

    @Override
    public LocalDateTime getTo() {
        return to;
    }
}