package com.example.Timetable_microservice.appointment.repository;

import com.example.Timetable_microservice.appointment.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {


    @Query(value = "SELECT * FROM appointment AS a WHERE a.active = false AND a.timetable_id = :id", nativeQuery = true)
    List<Appointment> findAllAvailableSlots(@Param("id") Long id);


    @Modifying
    @Query(value = "UPDATE appointment SET active = false, user_id = null WHERE user_id = :userId AND id = :appointmentId",nativeQuery = true)
    void updateSlotActiveToFalse(@Param("userId") Long userId, @Param("appointmentId") Long appointmentId);

    @Modifying
    @Query(value = "UPDATE appointment  SET active = true, user_id = :userId WHERE recording = :time",nativeQuery = true)
    void updateSlotActiveToTrue(@Param("time") LocalDateTime time,@Param("userId") Long userId);


    @Query(value = "SELECT a.user_id FROM appointment AS a WHERE a.id = :appointmentId ", nativeQuery = true)
    Long retrieveUserIdFromAppointment(@Param("appointmentId") Long id);
}