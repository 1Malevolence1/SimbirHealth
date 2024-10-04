package com.example.Hospital_microservice.hospital.repository;


import com.example.Hospital_microservice.hospital.model.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface HospitalRepository extends JpaRepository<Hospital, Long> {


    @Query(value = "SELECT DISTINCT h.* FROM hospital AS h JOIN rooms AS r USING(hospital_id) LIMIT :count OFFSET :from",nativeQuery = true)
    List<Hospital> findAllHospitalsWithParamFormAndCount(@Param("from") Integer from, @Param("count") Integer count);
}
