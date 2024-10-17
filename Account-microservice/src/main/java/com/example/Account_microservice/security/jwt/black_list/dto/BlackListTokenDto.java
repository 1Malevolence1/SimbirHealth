package com.example.Account_microservice.security.jwt.black_list.dto;

import java.time.LocalDateTime;

/**
 * DTO for {@link com.example.Account_microservice.security.jwt.black_list.model.BlackListToken}
 */
public record BlackListTokenDto(String token, LocalDateTime expirationTime) {
}