package com.example.Timetable_microservice.timetable.service;

import com.example.Timetable_microservice.timetable.model.Timetable;

public interface TimetableService {

    void save(Timetable timetable);

    void update(Timetable timetable);
}
