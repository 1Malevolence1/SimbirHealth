package com.example.Hospital_microservice.hospital.service.hospital;

import com.example.Hospital_microservice.hospital.config.ConstantResponseExceptionText;
import com.example.Hospital_microservice.hospital.dto.ResponseHospitalRoomsDto;
import com.example.Hospital_microservice.hospital.model.Hospital;
import com.example.Hospital_microservice.hospital.model.Room;
import com.example.Hospital_microservice.hospital.repository.HospitalRepository;
import com.example.Hospital_microservice.hospital.service.room.HospitalRoomsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
@Slf4j
public class HospitalServiceImpl implements HospitalService {

    private final HospitalRepository hospitalRepository;
    private final HospitalRoomsService hospitalRoomsService;

    @Override
    public void save(Hospital hospital) {
        hospitalRepository.save(hospital);
    }

    @Override
    public List<Hospital> getAllWithParamFromAndCount(Integer from, Integer count) {
            return hospitalRepository.findAllHospitalsWithParamFormAndCount(from, count);
    }

    @Override
    public Hospital findHospitalById(Long id) {
        return hospitalRepository.findById(id).orElseThrow(() -> new NoSuchElementException(ConstantResponseExceptionText.NOT_FOUND_HOSPITAL_BY_ID.formatted(id)));
    }

    @Override
    public List<Room> findAllHospitalRooms(Long id) {
        return hospitalRoomsService.getHospitalRoomsByIdHospital(id);
    }
}
