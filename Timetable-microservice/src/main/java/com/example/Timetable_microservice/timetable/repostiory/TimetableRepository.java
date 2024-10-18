package com.example.Timetable_microservice.timetable.repostiory;

import com.example.Timetable_microservice.timetable.model.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface TimetableRepository  extends JpaRepository<Timetable, Long> {

    @Modifying
    @Query(value = "DELETE FROM timetable AS t WHERE t.doctor_id = :doctorId", nativeQuery = true )
    void deleteAllByDoctorId(@Param(value = "doctorId") Long doctorId);

    @Modifying
    @Query(value = "DELETE FROM timetable AS t WHERE t.hospital_id = :hospitalId", nativeQuery = true )
    void deleteAllByHospitalId(@Param(value = "hospitalId") Long hospitalId);

    @Query(value = "SELECT t.* FROM timetable AS t " +
            "JOIN hospital AS h ON t.hospital_id = h.hospital_id " +
            "JOIN users AS u ON t.doctor_id = u.user_id " +
            "WHERE t.start_time >= :from AND t.end_time <= :to " +
            "AND t.hospital_id = :id AND h.deleted = false AND u.deleted = false", nativeQuery = true)
    List<Timetable> getAllTimetableWithParamsFromAndToByHospitalId(@Param("from") LocalDateTime from,
                                                                   @Param("to") LocalDateTime to,
                                                                   @Param("id") Long id);


    @Query(value = "SELECT t.* FROM timetable AS t " +
            "JOIN hospital AS h ON t.hospital_id = h.hospital_id " +
            "JOIN users AS u ON t.doctor_id = u.user_id " +
            "WHERE t.start_time >= :from AND t.end_time <= :to " +
            "AND t.doctor_id = :id AND h.deleted = false AND u.deleted = false", nativeQuery = true)
    List<Timetable> getAllTimetableWithParamsFromAndToByDoctorId(@Param("from") LocalDateTime from,
                                                                 @Param("to") LocalDateTime to,
                                                                 @Param("id") Long id);


    @Query(value = "SELECT t.* FROM timetable AS t " +
            "JOIN hospital AS h ON t.hospital_id = h.hospital_id " +
            "JOIN users AS u ON t.doctor_id = u.user_id " +
            "WHERE t.start_time >= :from AND t.end_time <= :to " +
            "AND t.hospital_id = :id AND t.room = :room AND h.deleted = false AND u.deleted = false", nativeQuery = true)
    List<Timetable> getAllTimetableWithParamsFromAndToByHospitalIdAndRoom(@Param("from") LocalDateTime from,
                                                                          @Param("to") LocalDateTime to,
                                                                          @Param("room") String room,
                                                                          @Param("id") Long id);


}
