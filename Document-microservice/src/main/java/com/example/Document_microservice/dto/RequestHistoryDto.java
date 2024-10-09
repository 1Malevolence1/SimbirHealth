package com.example.Document_microservice.dto;

import java.time.LocalDateTime;

/**
 * DTO for {@link com.example.Document_microservice.model.History}
 */
public record RequestHistoryDto(
        @jakarta.validation.constraints.NotNull(message = "поле {%s} не должно быть null") LocalDateTime date,
        @jakarta.validation.constraints.NotNull(message = "поле {%s} не должно быть  null") Long pacientId,
        @jakarta.validation.constraints.NotNull(message = "поле {%s} не должно быть  null") Long hospitalId,
        @jakarta.validation.constraints.NotNull(message = "поле {%s}  не должно быть  null") Long doctorId,
        @jakarta.validation.constraints.NotEmpty(message = "поле {%s} не должно быть пустым") String data) {
}