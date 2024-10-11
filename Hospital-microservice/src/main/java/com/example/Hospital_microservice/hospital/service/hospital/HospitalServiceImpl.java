package com.example.Hospital_microservice.hospital.service.hospital;

import com.example.Hospital_microservice.hospital.config.ConstantResponseExceptionText;
import com.example.Hospital_microservice.hospital.model.Hospital;
import com.example.Hospital_microservice.hospital.model.Room;
import com.example.Hospital_microservice.hospital.repository.HospitalRepository;
import com.example.Hospital_microservice.hospital.service.room.HospitalRoomsService;
import jakarta.transaction.Transactional;
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
    @Transactional
    public void save(Hospital hospital) {
        hospital.setDeleted(false);
        hospitalRepository.save(hospital);
    }

    @Override
    @Transactional
    public void update(Hospital hospital) {
        hospitalRepository.findById(hospital.getId()).ifPresentOrElse(
                updateHospital -> {
                    if(hospital.getName() != null) updateHospital.setName(hospital.getName());
                    if(hospital.getAddress() != null) updateHospital.setAddress(hospital.getAddress());
                    if(hospital.getContactPhone() != null) updateHospital.setContactPhone(hospital.getContactPhone());
                    if(hospital.getRooms() != null) {
                        hospitalRoomsService.deleteAllRoomsForHospital(hospital.getId());
                        updateHospital.setRooms(hospital.getRooms());
                    }
                }, () -> {
                    throw new NoSuchElementException(ConstantResponseExceptionText.NOT_FOUND_HOSPITAL_BY_ID.formatted(hospital.getId()));
                }
        );
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

    @Override
    public void deleteHospitalById(Long id) {
        hospitalRepository.deleteById(id);
        }
}

