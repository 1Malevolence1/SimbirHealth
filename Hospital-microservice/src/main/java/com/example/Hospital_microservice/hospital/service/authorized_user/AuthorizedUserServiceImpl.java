package com.example.Hospital_microservice.hospital.service.authorized_user;


import com.example.Hospital_microservice.hospital.convert.manager.ManagerMapperHospital;
import com.example.Hospital_microservice.hospital.dto.ResponseHospitalDto;
import com.example.Hospital_microservice.hospital.service.hospital.HospitalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorizedUserServiceImpl implements AuthorizedUserService {

    private final HospitalService hospitalService;
    private final ManagerMapperHospital mapperHospital;

    @Override
    public List<ResponseHospitalDto> getAllHospitals(Integer from, Integer count) {
        return mapperHospital.toDto(hospitalService.getAllWithParamFromAndCount(from, count));
    }

    @Override
    public ResponseHospitalDto getHospitalById(Long id) {
        return mapperHospital.toDto(hospitalService.findHospitalById(id));
    }

}

