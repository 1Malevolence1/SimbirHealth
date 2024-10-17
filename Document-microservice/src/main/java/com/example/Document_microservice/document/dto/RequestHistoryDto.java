package com.example.Document_microservice.document.dto;

import com.example.Document_microservice.document.model.History;

import java.time.LocalDateTime;

/**
 * DTO for {@link History}
 */
public record RequestHistoryDto(
        @jakarta.validation.constraints.NotNull(message = "поле {%s} не должно быть null") LocalDateTime date,
        @jakarta.validation.constraints.NotNull(message = "поле {%s} не должно быть  null") Long pacientId,
        @jakarta.validation.constraints.NotNull(message = "поле {%s} не должно быть  null") Long hospitalId,
        @jakarta.validation.constraints.NotNull(message = "поле {%s}  не должно быть  null") Long doctorId,
        @jakarta.validation.constraints.NotNull(message = "поле {%s}  не должно быть  null") String room,
        @jakarta.validation.constraints.NotEmpty(message = "поле {%s} не должно быть пустым") String data) {
}