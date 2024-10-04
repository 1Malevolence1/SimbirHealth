package com.example.Hospital_microservice.hospital.service.hospital;

import com.example.Hospital_microservice.hospital.model.Hospital;
import com.example.Hospital_microservice.hospital.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class HospitalServiceImpl implements HospitalService {

    private final HospitalRepository hospitalRepository;

    @Override
    public void save(Hospital hospital) {
        hospitalRepository.save(hospital);
    }

    @Override
    public List<Hospital> getAllWithParamFromAndCount(Integer from, Integer count) {
            return hospitalRepository.findAllHospitalsWithParamFormAndCount(from, count);
    }
}
