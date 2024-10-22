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
    @Query(value = "UPDATE appointment SET active = false, user_id = null WHERE user_id = :userId AND appointment_id = :appointmentId",nativeQuery = true)
    void updateSlotActiveToFalse(@Param("userId") Long userId, @Param("appointmentId") Long appointmentId);


    @Modifying
    @Query(value = "UPDATE appointment SET active = false, user_id = null WHERE appointment_id = :appointmentId",nativeQuery = true)
    void updateSlotActiveToFalse(@Param("appointmentId") Long appointmentId);


    @Modifying
    @Query(value = "UPDATE appointment SET active = true, user_id = :userId WHERE recording = :time and  timetable_id= :timetableId",nativeQuery = true)
    void updateSlotActiveToTrue(@Param("time") LocalDateTime time, @Param("timetableId") Long timetableId, @Param("userId") Long userId);


    @Query(value = "SELECT a.user_id FROM appointment AS a WHERE a.appointment_id = :appointmentId ", nativeQuery = true)
    Long retrieveUserIdFromAppointment(@Param("appointmentId") Long id);


    @Query(value = "SELECT COUNT(a.user_id) FROM appointment AS a WHERE a.timetable_id = :appointmentId ", nativeQuery = true)
    Long retrieveCountUserIdFromAppointment(@Param("appointmentId") Long id);


    @Query(value = "SELECT a.user_id FROM appointment AS a JOIN timetable AS t USING(timetable_id) WHERE a.recording = :date AND t.timetable_id = :timetable_id", nativeQuery = true)
    Long userIdInData(@Param("timetable_id") Long timetable_id, @Param("date") LocalDateTime data);


    @Modifying
    @Query(value = "DELETE FROM appointment WHERE appointment.timetable_id = :timetableId", nativeQuery = true)
    void deleteAllByIdTimetable(@Param("timetableId") Long timetableId);



}
