package com.example.Hospital_microservice.hospital.service.authorized_user;


import com.example.Hospital_microservice.hospital.dto.ResponseHospitalDto;

import java.util.List;

public interface AuthorizedUserService {

    List<ResponseHospitalDto> getAllHospitals(Integer from, Integer count);

    ResponseHospitalDto getHospitalById(Long id);
}
