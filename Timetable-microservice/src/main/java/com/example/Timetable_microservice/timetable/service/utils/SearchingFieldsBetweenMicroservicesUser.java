package com.example.Timetable_microservice.timetable.service.utils;

public interface SearchingFieldsBetweenMicroservicesUser {

    Long getUserId(String token);

    String getRole(String token);
}
