package com.example.Timetable_microservice.timetable.controller;

import com.example.Timetable_microservice.appointment.dto.appointment.ResponseAppointmentsDto;
import com.example.Timetable_microservice.timetable.config.ConstantResponseSuccessfulText;
import com.example.Timetable_microservice.timetable.dto.RequestTimetableDto;
import com.example.Timetable_microservice.timetable.dto.ResponseTimetableDto;
import com.example.Timetable_microservice.timetable.service.AdminAndManagerService.AdminAndManagerService;
import com.example.Timetable_microservice.timetable.service.AuthorizationHeaderExtractor;
import com.example.Timetable_microservice.timetable.service.MicroserviceEntityChecker;
import com.example.Timetable_microservice.timetable.service.SearchingFieldsBetweenMicroservicesUser;
import com.example.Timetable_microservice.timetable.service.authorized_user.AuthorizedUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/Timetable")
@RequiredArgsConstructor
@Slf4j
public class TimetableRestController {

    private final AdminAndManagerService adminAndManagerService;
    private final AuthorizedUserService authorizedUserService;
    private final MicroserviceEntityChecker microserviceEntityChecker;
    private final SearchingFieldsBetweenMicroservicesUser searchingFieldsBetweenMicroservicesUser;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER')")
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
            return ResponseEntity.ok().body(ConstantResponseSuccessfulText.SAVE_TIMETABLE);
        }
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER')")
    @PutMapping("{timetableId:\\d+}")
    public ResponseEntity<String> updateTimetable(@Valid @RequestBody RequestTimetableDto dto, BindingResult bindingResult,
                                            @RequestHeader("Authorization") String authorizationHeader,
                                            @PathVariable(name = "timetableId") Long timetableId) throws BindException {

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
            adminAndManagerService.update(dto, timetableId);
            return ResponseEntity.ok().body(ConstantResponseSuccessfulText.UPDATE_TIMETABLE.formatted(timetableId));
        }
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER')")
    @DeleteMapping("{timetableId:\\d+}")
    public ResponseEntity<String> deleteOneTimetableById(@PathVariable(name = "timetableId") Long id) {
        adminAndManagerService.deleteById(id);
        return ResponseEntity.ok().body(ConstantResponseSuccessfulText.DELETE_TIMETABLE.formatted(id));
    }


    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER')")
    @DeleteMapping("/Doctor/{doctorId:\\d+}")
    public ResponseEntity<String> deleteAllTimetablesByDoctorId(@PathVariable(name = "doctorId") Long id,
                                                                @RequestHeader("Authorization") String authorizationHeader) {
        microserviceEntityChecker.checkEntityForUser(
                id,
                AuthorizationHeaderExtractor.getJwtToken(authorizationHeader));

        adminAndManagerService.deleteAllByDoctorId(id);
        return ResponseEntity.ok().body(ConstantResponseSuccessfulText.DELETE_TIMETABLE_FOR_DOCTOR.formatted(id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER')")
    @DeleteMapping("/Hospital/{hospitalId:\\d+}")
    public ResponseEntity<String> deleteAllTimetablesByHospitalId(@PathVariable(name = "hospitalId") Long id,
                                                                  @RequestHeader("Authorization") String authorizationHeader) {
        log.info("{}", authorizationHeader);

        microserviceEntityChecker.checkEntityForHospital(
                id,
                AuthorizationHeaderExtractor.getJwtToken(authorizationHeader));

        adminAndManagerService.deleteAllByHospitalId(id);
        return ResponseEntity.ok().body(ConstantResponseSuccessfulText.DELETE_TIMETABLE_FOR_HOSPITAL.formatted(id));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/Hospital/{hospitalId:\\d+}")
    public ResponseEntity<List<ResponseTimetableDto>> getAllTimetableByHospitalId(@PathVariable(name = "hospitalId") Long id,
                                                                                  @RequestParam(name = "from") String from,
                                                                                  @RequestParam(name = "to") String to,
                                                                                  @RequestHeader("Authorization") String authorizationHeader) {
        microserviceEntityChecker.checkEntityForHospital(
                id,
                AuthorizationHeaderExtractor.getJwtToken(authorizationHeader)
        );

        LocalDateTime fromDateTime = LocalDateTime.parse(from.replace("Z", ""));
        LocalDateTime toDateTime = LocalDateTime.parse(to.replace("Z", ""));

        return ResponseEntity.ok().body(
                authorizedUserService.getAllTimetableByHospitalId(fromDateTime, toDateTime, id)
        );
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/Doctor/{doctorId:\\d+}")
    public ResponseEntity<List<ResponseTimetableDto>> getAllTimetableByDoctorId(@PathVariable(name = "doctorId") Long id,
                                                                                @RequestParam(name = "from") String from,
                                                                                @RequestParam(name = "to") String to,
                                                                                @RequestHeader("Authorization") String authorizationHeader) {
        microserviceEntityChecker.checkEntityForUser(
                id,
                AuthorizationHeaderExtractor.getJwtToken(authorizationHeader)
        );

        LocalDateTime fromDateTime = LocalDateTime.parse(from.replace("Z", ""));
        LocalDateTime toDateTime = LocalDateTime.parse(to.replace("Z", ""));

        return ResponseEntity.ok().body(
                authorizedUserService.getAllTimetableByDoctorById(fromDateTime, toDateTime, id)
        );
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_DOCTOR')")
    @GetMapping("/Hospital/{hospitalId:\\d+}/Room/{room}")
    public ResponseEntity<List<ResponseTimetableDto>> getAllTimetableByHospitalIdAndByRoom(
            @PathVariable(name = "hospitalId") Long id,
            @PathVariable(name = "room") String room,
            @RequestParam(name = "from") String from,
            @RequestParam(name = "to") String to,
            @RequestHeader("Authorization") String authorizationHeader) {
        microserviceEntityChecker.checkEntityForHospital(
                id,
                room,
                AuthorizationHeaderExtractor.getJwtToken(authorizationHeader)
        );

        LocalDateTime fromDateTime = LocalDateTime.parse(from.replace("Z", ""));
        LocalDateTime toDateTime = LocalDateTime.parse(to.replace("Z", ""));

        return ResponseEntity.ok().body(
                authorizedUserService.getAllTimetableByHospitalIdAndByRoom(fromDateTime, toDateTime, room, id)
        );
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("{timetableId:\\d+}/Appointments")
    public ResponseEntity<List<ResponseAppointmentsDto>> getAvailableAppointment(@PathVariable(name = "timetableId") Long id) {
        microserviceEntityChecker.checkEntityTimetable(id);
        return ResponseEntity.ok().body(
                authorizedUserService.getAllAvailableSlotsByIdTimetable(id)
        );
    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping("{timetableId:\\d+}/Appointments")
    public ResponseEntity<String> makeAppointment(@PathVariable(name = "timetableId") Long id,
                                                  @RequestParam("time") LocalDateTime time,
                                                  @RequestHeader("Authorization") String authorizationHeader) {
        microserviceEntityChecker.checkEntityTimetable(id);

        authorizedUserService.makeAppointment(
                time,
                searchingFieldsBetweenMicroservicesUser.getUserId(authorizationHeader)
        );

        return ResponseEntity.ok().body(
                ConstantResponseSuccessfulText.UPDATE_APPOINTMENT_TRUE.formatted(time.toString())
        );
    }
}
