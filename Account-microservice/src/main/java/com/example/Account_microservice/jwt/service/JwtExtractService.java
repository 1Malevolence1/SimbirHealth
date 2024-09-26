package com.example.Account_microservice.jwt.service;

import com.example.Account_microservice.jwt.dto.JwtAuthority;
import com.example.Account_microservice.user.model.Role;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface JwtExtractService {
    Long extractUserId(String token);
    String extractUserName(String token);
    String extractSubject(String token);
    Date extractIssuedAt(String token);
    Date extractExpiration(String token);
    List<JwtAuthority> extractRole(String token);
}
