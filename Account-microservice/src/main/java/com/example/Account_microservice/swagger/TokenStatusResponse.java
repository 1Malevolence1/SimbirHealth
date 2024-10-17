package com.example.Account_microservice.swagger;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "статус токена")
public record TokenStatusResponse(
        @Schema(description = "Статус токена (активен или нет)", example = "true")
        String active
) {
}
