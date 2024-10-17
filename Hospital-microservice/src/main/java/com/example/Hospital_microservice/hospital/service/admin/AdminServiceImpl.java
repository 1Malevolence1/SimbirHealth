package com.example.Hospital_microservice.hospital.service.admin;

import com.example.Hospital_microservice.hospital.convert.manager.ManagerMapperHospital;
import com.example.Hospital_microservice.hospital.dto.RequestCreateHospitalDto;
import com.example.Hospital_microservice.hospital.dto.RequestUpdateHospitalDto;
import com.example.Hospital_microservice.hospital.service.hospital.HospitalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;



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
    public void deleteHospital(Long id) {
        hospitalService.deleteHospitalById(id);
    }

    @Override
    public void updateHospital(RequestUpdateHospitalDto updateHospitalDto, Long id) {
        hospitalService.update(
                mapperHospital.toModel(updateHospitalDto),
                id
        );
    }
}
