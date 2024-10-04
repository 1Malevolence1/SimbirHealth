package com.example.Hospital_microservice.hospital.service.hospital;

import com.example.Hospital_microservice.hospital.model.Hospital;
import com.example.Hospital_microservice.hospital.model.Room;

import java.util.List;

public interface HospitalService  {

    void save(Hospital hospital);

    List<Hospital> getAllWithParamFromAndCount(Integer from, Integer count);

    Hospital findHospitalById(Long id);

    List<Room> findAllHospitalRooms(Long id);

    void deleteHospitalById(Long id);
}
