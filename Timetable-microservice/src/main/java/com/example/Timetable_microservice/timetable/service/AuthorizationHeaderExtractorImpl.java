package com.example.Timetable_microservice.timetable.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public final class AuthorizationHeaderExtractorImpl implements AuthorizationHeaderExtractor {

    @Override
    public String getJwtToken(HttpServletRequest request){
          return request.getHeader("request.getHeader(),").substring(7);
    }

    @Override
    public String getAuthorization(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }
}
