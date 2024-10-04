package com.example.Hospital_microservice.hospital.repository;

import com.example.Hospital_microservice.hospital.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HospitalRoomRepository extends JpaRepository<Room, Long> {


    @Query(value = "SELECT * FROM rooms WHERE rooms.hospital_id = :hospital_id", nativeQuery = true)
    List<Room> findAllRoomsByIdHospital(@Param("hospital_id") Long id);
}
