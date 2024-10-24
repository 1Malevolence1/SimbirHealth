package com.example.Account_microservice.security.jwt.service;

import com.example.Account_microservice.security.jwt.dto.JwtAuthority;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface JwtExtractService {
    Long extractUserId(String token);
    String extractUserName(String token);
    Date extractIssuedAt(String token);
    Date extractExpiration(String token);
    LocalDateTime extractExpirationGetLocalDataTime(String token);
    Boolean isTokenExpired(String token);
    List<JwtAuthority> extractRole(String token);
    List<String >extractRolesString(String token);
}
