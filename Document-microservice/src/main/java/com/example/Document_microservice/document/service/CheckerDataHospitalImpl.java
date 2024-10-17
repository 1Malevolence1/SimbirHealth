package com.example.Document_microservice.document.service;


import com.example.Document_microservice.document.config.ConstantResponseExceptionText;
import com.example.Document_microservice.document.dto.ResponseRoom;
import com.example.Document_microservice.document.dto.ResponseRoomsHospitalDto;
import com.example.Document_microservice.document.exeption.RoomNotFoundException;
import com.example.Document_microservice.document.exeption.Validate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class CheckerDataHospitalImpl implements CheckerDataHospital {

    private final ContactingMicroserviceHospitalImpl contactingMicroserviceHospital;

    @Override
    public void checkHospital(String token, Long hospitalId, String room) {
        ResponseRoomsHospitalDto roomsHospitalDto = contactingMicroserviceHospital.requestCheckHospital(token, hospitalId);

        if (roomsHospitalDto == null || roomsHospitalDto.rooms() == null) {
            throw new NoSuchElementException("Не удалось получить данные о больнице");
        }

        if (!checkRoom(room, roomsHospitalDto.rooms())) {
            throw new RoomNotFoundException(new Validate(ConstantResponseExceptionText.NOT_FOUND_ROOM_IN_HOSPITAL_BY_ID.formatted(room, hospitalId)));
        }
    }

    private boolean checkRoom(String room, List<ResponseRoom> listRooms){
        List<String> rooms = listRooms.stream().map(ResponseRoom::title).toList();
        return rooms.contains(room);
    }
}

