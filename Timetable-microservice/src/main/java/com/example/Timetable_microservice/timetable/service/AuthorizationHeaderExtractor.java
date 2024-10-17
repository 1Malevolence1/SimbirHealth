package com.example.Timetable_microservice.timetable.service;

import jakarta.servlet.http.HttpServletRequest;

public interface AuthorizationHeaderExtractor {
    String getJwtToken(HttpServletRequest request);
    String getAuthorization(HttpServletRequest request);
}
