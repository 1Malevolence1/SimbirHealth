package com.example.Timetable_microservice.timetable.annotation;

import java.time.LocalDateTime;

public interface TimetableValidate {

    LocalDateTime getFrom();
    LocalDateTime getTo();
}
