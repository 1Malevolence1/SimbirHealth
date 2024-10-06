package com.example.Timetable_microservice.timetable.repostiory;

import com.example.Timetable_microservice.timetable.model.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

public interface TimetableRepository  extends JpaRepository<Timetable, Long> {

    @Modifying
    @Query(value = "DELETE FROM timetable AS t WHERE t.doctor_id = :doctorId", nativeQuery = true )
    void deleteAllByDoctorId(@Param(value = "doctorId") Long doctorId);
}
