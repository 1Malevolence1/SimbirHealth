package com.example.Document_microservice.document.dto;

import com.example.Document_microservice.document.model.History;

import java.time.LocalDateTime;

/**
 * DTO for {@link History}
 */
public record ResponseHistoryDto(Long id, LocalDateTime date, Long pacientId, Long hospitalId, Long doctorId,
                                 String data, String room) {
}