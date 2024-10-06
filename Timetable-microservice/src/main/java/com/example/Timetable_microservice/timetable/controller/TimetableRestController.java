package com.example.Timetable_microservice.timetable.controller;

import com.example.Timetable_microservice.timetable.dto.RequestTimetableDto;
import com.example.Timetable_microservice.timetable.service.AdminAndManagerService.AdminAndManagerService;
import com.example.Timetable_microservice.timetable.service.AuthorizationHeaderExtractor;
import com.example.Timetable_microservice.timetable.service.MicroserviceEntityChecker;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Timetable")
@RequiredArgsConstructor
@Slf4j
public class TimetableRestController {

    private final AdminAndManagerService adminAndManagerService;
    private final MicroserviceEntityChecker microserviceEntityChecker;

    @PostMapping()
    public ResponseEntity<String> addTimetable(@Valid @RequestBody RequestTimetableDto dto, BindingResult bindingResult,
                                               @RequestHeader("Authorization") String authorizationHeader) throws BindException {

        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException bindException) {
                throw bindException;
            } else {
                throw new BindException(bindingResult);
            }
        } else {

            String token = AuthorizationHeaderExtractor.getJwtToken(authorizationHeader);

            log.info("{}", token);
            microserviceEntityChecker.checkEntityForHospital(dto.hospitalId(), dto.room(), token);
            microserviceEntityChecker.checkEntityForUser(dto.doctorId(), token);
            adminAndManagerService.add(dto);
            return ResponseEntity.ok().body("Запись успешно добавлена");
        }
    }


    @PutMapping("{timetableId:\\d+}")
    public ResponseEntity<String> Timetable(@Valid @RequestBody RequestTimetableDto dto, BindingResult bindingResult,
                                               @RequestHeader("Authorization") String authorizationHeader,
                                            @PathVariable(name = "timetableId") Long id) throws BindException {

        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException bindException) {
                throw bindException;
            } else {
                throw new BindException(bindingResult);
            }
        } else {


            String token = AuthorizationHeaderExtractor.getJwtToken(authorizationHeader);

            log.info("{}", token);
            microserviceEntityChecker.checkEntityForHospital(dto.hospitalId(), dto.room(), token);
            microserviceEntityChecker.checkEntityForUser(dto.doctorId(), token);
            adminAndManagerService.update(dto, id);
            return ResponseEntity.ok().body("Запись успешно обнавлена");
        }
    }


}
