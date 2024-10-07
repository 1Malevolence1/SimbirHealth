package com.example.Timetable_microservice.appointment.repository;

import com.example.Timetable_microservice.appointment.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {


    @Query(value = "SELECT * FROM appointment AS a WHERE a.active = false AND a.timetable_id = :id", nativeQuery = true)
    List<Appointment> findAllAvailableSlots(Long id);


    @Modifying
    @Query(value = "UPDATE FROM appointment AS a SET a.active = false WHERE a.id = :id ",nativeQuery = true)
    void updateSlotActiveToFalse(Long id);

    @Modifying
    @Query(value = "UPDATE FROM appointment AS a SET a.active = true WHERE a.id = :id ",nativeQuery = true)
    void updateSlotActiveToTrue(Long id);
}
