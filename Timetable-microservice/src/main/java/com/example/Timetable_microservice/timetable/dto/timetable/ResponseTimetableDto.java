package com.example.Timetable_microservice.timetable.dto.timetable;

import java.time.LocalDateTime;

/**
 * DTO for {@link com.example.Timetable_microservice.timetable.model.Timetable}
 */
public record ResponseTimetableDto(Long id, Long hospitalId, Long doctorId, LocalDateTime from, LocalDateTime to,
                                   String room) {
}