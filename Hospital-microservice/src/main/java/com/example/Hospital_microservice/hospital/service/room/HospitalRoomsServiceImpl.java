package com.example.Hospital_microservice.hospital.service.room;

import com.example.Hospital_microservice.hospital.model.Room;
import com.example.Hospital_microservice.hospital.repository.HospitalRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class HospitalRoomsServiceImpl implements HospitalRoomsService {

    private final HospitalRoomRepository hospitalRoomRepository;

    @Override
    public List<Room> getHospitalRoomsByIdHospital(Long id) {
        return hospitalRoomRepository.findAllRoomsByIdHospital(id);
    }

    @Override
    public void deleteAllRoomsForHospital(Long id) {
        hospitalRoomRepository.deleteAllRoomsForHospitalById(id);
    }
}
