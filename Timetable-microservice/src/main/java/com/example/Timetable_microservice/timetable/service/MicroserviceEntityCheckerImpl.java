package com.example.Timetable_microservice.timetable.service;


import com.example.Timetable_microservice.timetable.dto.hopsital.ResponseCheckHospitalDto;
import com.example.Timetable_microservice.timetable.exception.UnauthorizedException;
import com.example.Timetable_microservice.timetable.exception.Validate;
import com.example.Timetable_microservice.timetable.service.hospitalChcker.HospitalCheckerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
@Slf4j
public class MicroserviceEntityCheckerImpl implements MicroserviceEntityChecker {


    private final RestClient restClientForUser;
    private final HospitalCheckerService hospitalCheckerService;

    @Override
    public void checkEntityForHospital(Long id, String room, String token) {

            log.info("Начался поиск больницы");
            ResponseCheckHospitalDto dto = hospitalCheckerService.find(id, token);
            hospitalCheckerService.checkRoomForHospital(room, dto, id);
            log.info("Поиск больницы заврешился успешно");
    }

    @Override
    public void checkEntityForUser(Long id, String token) {
        try {
            log.info("Начался поиск доктора");
            restClientForUser.get().uri("/api/Doctors/{id}", id)
                    .header("Authorization", "Bearer " + token)
                    .retrieve().toEntity(Void.class);
            log.info("Поиск доктора заврешился успешно");
        } catch (HttpClientErrorException exception) {
            if (exception.getStatusCode() == HttpStatus.NOT_FOUND) {
                log.error("доктор не найдена");
                throw new NoSuchElementException(exception.getMessage());
            }

            if (exception.getStatusCode() == HttpStatus.FORBIDDEN) {
                log.error("ошибка с аунтификацией");
                throw new UnauthorizedException(new Validate(exception.getMessage()));
            }
        }
    }
}
