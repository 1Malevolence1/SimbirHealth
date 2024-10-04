package com.example.Hospital_microservice.hospital.service.admin;

import com.example.Hospital_microservice.hospital.convert.manager.ManagerMapperHospital;
import com.example.Hospital_microservice.hospital.dto.RequestCreateHospitalDto;
import com.example.Hospital_microservice.hospital.dto.ResponseHospitalDto;
import com.example.Hospital_microservice.hospital.model.Hospital;
import com.example.Hospital_microservice.hospital.service.hospital.HospitalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {

    private final HospitalService hospitalService;
    private final ManagerMapperHospital mapperHospital;

    @Override
    public void addHospital(RequestCreateHospitalDto requestCreateHospitalDto) {
        hospitalService.save(
                mapperHospital.toModel(
                        requestCreateHospitalDto
                )
        );
    }

    @Override
    public List<ResponseHospitalDto> getAllHospitals(Integer from, Integer count) {
        if(from == null) from = 0;
        if(count == null) count = Integer.MAX_VALUE;
        return mapperHospital.toDto(hospitalService.getAllWithParamFromAndCount(from, count));
    }


}
