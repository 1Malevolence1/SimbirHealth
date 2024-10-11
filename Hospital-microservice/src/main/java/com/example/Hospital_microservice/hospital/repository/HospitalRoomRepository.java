package com.example.Hospital_microservice.hospital.repository;

import com.example.Hospital_microservice.hospital.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HospitalRoomRepository extends JpaRepository<Room, Long> {


    @Query(value = "SELECT r.* FROM room r JOIN hospital h USING(hospital_id) WHERE h.hospital_id = :hospital_id AND h.deleted = FALSE", nativeQuery = true)
    List<Room> findAllRoomsByIdHospital(@Param("hospital_id") Long id);


    @Modifying
    @Query(value = "DELETE FROM room WHERE hospital_id = :hospital_id", nativeQuery = true)
    void deleteAllRoomsForHospitalById(@Param("hospital_id") Long id);
}
