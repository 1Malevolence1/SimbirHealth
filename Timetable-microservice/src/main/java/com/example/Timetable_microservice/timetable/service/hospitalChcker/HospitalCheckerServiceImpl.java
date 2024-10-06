package com.example.Timetable_microservice.timetable.service.hospitalChcker;

import com.example.Timetable_microservice.timetable.config.ConstantResponseExceptionText;
import com.example.Timetable_microservice.timetable.dto.hopsital.ResponseCheckHospitalDto;

import com.example.Timetable_microservice.timetable.dto.hopsital.ResponseHospitalRooms;
import com.example.Timetable_microservice.timetable.exception.NoFindRoomInHospitalException;
import com.example.Timetable_microservice.timetable.exception.UnauthorizedException;
import com.example.Timetable_microservice.timetable.exception.Validate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
public class HospitalCheckerServiceImpl implements HospitalCheckerService {

    private final RestClient restClientForHospital;

    @Override
    public ResponseCheckHospitalDto find(Long id, String token) {
        try {

           return restClientForHospital.get().uri("/api/Hospitals/{id}", id)
                    .header("Authorization", "Bearer " + token)
                    .retrieve().body(ResponseCheckHospitalDto.class);

        } catch (HttpClientErrorException exception) {
            if (exception.getStatusCode() == HttpStatus.NOT_FOUND) {
                log.error("больница не найдена");
             throw new NoSuchElementException(exception.getMessage());
            }

            if (exception.getStatusCode() == HttpStatus.FORBIDDEN) {
                log.error("ошибка с аунтификацией");
                throw new UnauthorizedException(new Validate(exception.getMessage()));
            }
        }
        return null;
    }

    @Override
    public void checkRoomForHospital(String room, ResponseCheckHospitalDto dto, Long id) {
        List<String> listRooms = covertRoomInListString(dto.rooms());
        if(!listRooms.contains(room)){
            throw new NoFindRoomInHospitalException(
                    new Validate(
                            ConstantResponseExceptionText.
                                    NOT_FOUND_ROOM_BY_ID_HOSPITAL
                                    .formatted(room, id)
                    ));
        }
    }

    private List<String> covertRoomInListString(List<ResponseHospitalRooms> listRoom){
        return listRoom.stream().map(ResponseHospitalRooms::title).toList();
    }
}
