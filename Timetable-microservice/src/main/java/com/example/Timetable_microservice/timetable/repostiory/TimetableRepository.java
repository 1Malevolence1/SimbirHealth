package com.example.Timetable_microservice.timetable.repostiory;

import com.example.Timetable_microservice.timetable.model.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimetableRepository  extends JpaRepository<Timetable, Long> {
}
