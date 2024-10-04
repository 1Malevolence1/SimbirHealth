package com.example.Hospital_microservice.hospital.convert.manager;

import com.example.Hospital_microservice.hospital.convert.mapper.MapperHospitalListRooms;
import com.example.Hospital_microservice.hospital.convert.mapper.MapperHospitalRoom;
import com.example.Hospital_microservice.hospital.dto.ResponseHospitalRoomsDto;
import com.example.Hospital_microservice.hospital.model.Room;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor

public class ManagerMapperHospitalRoomsImpl implements ManagerMapperHospitalRooms {


    private final MapperHospitalListRooms mapperHospitalListRooms;

    @Override
    public List<ResponseHospitalRoomsDto> toDto(List<Room> rooms) {
        return mapperHospitalListRooms.toDto(rooms);
    }
}
