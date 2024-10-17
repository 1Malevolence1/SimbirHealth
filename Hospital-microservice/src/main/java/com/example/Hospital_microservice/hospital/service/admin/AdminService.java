package com.example.Hospital_microservice.hospital.service.admin;

import com.example.Hospital_microservice.hospital.dto.RequestCreateHospitalDto;
import com.example.Hospital_microservice.hospital.dto.RequestUpdateHospitalDto;

public interface AdminService {

    void addHospital(RequestCreateHospitalDto requestCreateHospitalDto);
    void deleteHospital(Long id);
    void updateHospital(RequestUpdateHospitalDto updateHospitalDto, Long id);
}
