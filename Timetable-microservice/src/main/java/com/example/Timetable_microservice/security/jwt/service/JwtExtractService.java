package com.example.Timetable_microservice.security.jwt.service;


import java.util.Date;
import java.util.List;

public interface JwtExtractService {

    Boolean isTokenExpired(String token);

    Date extractExpiration(String token);

    String extractUserName(String token);

    Long extractUserId(String token);

    List<String> extractRoles(String token);

    boolean isTokenValid(String token, String username);
}
