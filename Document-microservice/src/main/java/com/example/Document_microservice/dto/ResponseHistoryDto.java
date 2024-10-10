package com.example.Document_microservice.dto;

import java.time.LocalDateTime;

/**
 * DTO for {@link com.example.Document_microservice.model.History}
 */
public record ResponseHistoryDto(Long id, LocalDateTime date, Long pacientId, Long hospitalId, Long doctorId,
                                 String data, String room) {
}