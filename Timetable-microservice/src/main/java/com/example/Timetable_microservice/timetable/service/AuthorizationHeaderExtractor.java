package com.example.Timetable_microservice.timetable.service;

public final class AuthorizationHeaderExtractor {

    public static  String getJwtToken(String authorizationHeader){
        return authorizationHeader.startsWith("Bearer ") ?
                authorizationHeader.substring(7) : authorizationHeader;
    }
}
