package com.example.Timetable_microservice.timetable.controller;

import com.example.Timetable_microservice.appointment.dto.appointment.ResponseAppointmentsDto;
import com.example.Timetable_microservice.timetable.config.ConstantResponseSuccessfulText;
import com.example.Timetable_microservice.timetable.dto.timetable.RequestTimetableDto;
import com.example.Timetable_microservice.timetable.dto.timetable.ResponseTimetableDto;
import com.example.Timetable_microservice.timetable.exception.Validate;
import com.example.Timetable_microservice.timetable.service.AdminAndManagerService.AdminAndManagerService;
import com.example.Timetable_microservice.timetable.service.authorized_user.AuthorizedUserService;
import com.example.Timetable_microservice.timetable.service.utils.AuthorizationHeaderExtractor;
import com.example.Timetable_microservice.timetable.service.utils.MicroserviceEntityChecker;
import com.example.Timetable_microservice.timetable.service.utils.SearchingFieldsBetweenMicroservicesUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
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
@Tag(name = "Timetable", description = "запросы начинающиеся с Timetable")
public class TimetableRestController {

    private final AdminAndManagerService adminAndManagerService;
    private final AuthorizedUserService authorizedUserService;
    private final MicroserviceEntityChecker microserviceEntityChecker;
    private final SearchingFieldsBetweenMicroservicesUser searchingFieldsBetweenMicroservicesUser;
    private final AuthorizationHeaderExtractor authorizationHeader;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER')")
    @PostMapping()
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "Создание новой записи в расписании", description = "Только администраторы и менеджеры. {from} и {to} - количество\n" +
            "минут всегда кратно 30, секунды всегда 0 (пример: “2024-04-25T11:30:00Z”, “2024-\n" +
            "04-25T12:00:00Z”). {to} > {from}. Разница между {to} и {from} не должна превышать\n" +
            "12 часов.")
    @SecurityRequirement(name = "JWT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(type = "string", example = ConstantResponseSuccessfulText.SAVE_TIMETABLE))),
            @ApiResponse(responseCode = "400", description = "Вернётся список ошибок, если будут неккоректные данные", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Validate.class)))),
            @ApiResponse(responseCode = "404", description = "Елси не будет надена больница или доктор, то вернётся ошибка", content = @Content(schema = @Schema(implementation = Validate.class)))
    })
    public ResponseEntity<String> addTimetable(@Valid @RequestBody RequestTimetableDto dto, BindingResult bindingResult,
                                               HttpServletRequest request) throws BindException {

        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException bindException) {
                throw bindException;
            } else {
                throw new BindException(bindingResult);
            }
        } else {

            String token = authorizationHeader.getJwtToken(request);

            log.info("{}", token);
            microserviceEntityChecker.checkEntityForHospital(dto.hospitalId(), dto.room(), token);
            microserviceEntityChecker.checkEntityForUser(dto.doctorId(), token);
            adminAndManagerService.add(dto);
            return ResponseEntity.ok().body(ConstantResponseSuccessfulText.SAVE_TIMETABLE);
        }
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER')")
    @PutMapping("{timetableId:\\d+}")
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "Обновление записи расписания", description = "Только администраторы и менеджеры. {from} и {to} - количество\n" +
            "минут всегда кратно 30, секунды всегда 0 (пример: “2024-04-25T11:30:00Z”, “2024-\n" +
            "04-25T12:00:00Z”). {to} > {from}. Разница между {to} и {from} не должна превышать\n" +
            "12 часов.")
    @SecurityRequirement(name = "JWT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(type = "string", example = ConstantResponseSuccessfulText.SAVE_TIMETABLE))),
            @ApiResponse(responseCode = "400", description = "Вернётся список ошибок, если будут неккоректные данные", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Validate.class)))),
            @ApiResponse(responseCode = "404", description = "Елси не будет надена больница или доктор, то вернётся ошибка", content = @Content(schema = @Schema(implementation = Validate.class)))
    })
    public ResponseEntity<String> updateTimetable(@Valid @RequestBody RequestTimetableDto dto, BindingResult bindingResult,
                                                  HttpServletRequest request,
                                                  @PathVariable(name = "timetableId") Long timetableId) throws BindException {

        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException bindException) {
                throw bindException;
            } else {
                throw new BindException(bindingResult);
            }
        } else {


            String token = authorizationHeader.getJwtToken(request);

            log.info("{}", token);
            microserviceEntityChecker.checkEntityForHospital(dto.hospitalId(), dto.room(), token);
            microserviceEntityChecker.checkEntityForUser(dto.doctorId(), token);
            adminAndManagerService.update(dto, timetableId);
            return ResponseEntity.ok().body(ConstantResponseSuccessfulText.UPDATE_TIMETABLE.formatted(timetableId));
        }
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER')")
    @DeleteMapping("{timetableId:\\d+}")
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "Удаление записи расписания", description = "Только администраторы и менеджеры")
    public ResponseEntity<String> deleteOneTimetableById(@PathVariable(name = "timetableId") Long id) {
        adminAndManagerService.deleteById(id);
        return ResponseEntity.ok().body(ConstantResponseSuccessfulText.DELETE_TIMETABLE.formatted(id));
    }


    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER')")
    @DeleteMapping("/Doctor/{doctorId:\\d+}")
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "Удаление записей расписания доктора", description = "Только администраторы и менеджеры")
    @SecurityRequirement(name = "JWT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(type = "string", example = ConstantResponseSuccessfulText.DELETE_TIMETABLE_FOR_DOCTOR))),
            @ApiResponse(responseCode = "404", description = "Елси не будет наден доктор, то вернётся ошибка", content = @Content(schema = @Schema(implementation = Validate.class)))
    })
    public ResponseEntity<String> deleteAllTimetablesByDoctorId(@PathVariable(name = "doctorId") Long id,
                                                                HttpServletRequest request) {
        microserviceEntityChecker.checkEntityForUser(
                id,
                authorizationHeader.getJwtToken(request));

        adminAndManagerService.deleteAllByDoctorId(id);
        return ResponseEntity.ok().body(ConstantResponseSuccessfulText.DELETE_TIMETABLE_FOR_DOCTOR.formatted(id));
    }



    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER')")
    @DeleteMapping("/Hospital/{hospitalId:\\d+}")
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "Удаление записей расписания больницы", description = "Только администраторы и менеджеры")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(type = "string", example = ConstantResponseSuccessfulText.DELETE_TIMETABLE_FOR_HOSPITAL))),
            @ApiResponse(responseCode = "404", description = "Елси не будет надена больница, то вернётся ошибка", content = @Content(schema = @Schema(implementation = Validate.class)))
    })
    public ResponseEntity<String> deleteAllTimetablesByHospitalId(@PathVariable(name = "hospitalId") Long id,
                                                                  HttpServletRequest request) {
        log.info("{}", authorizationHeader);

        microserviceEntityChecker.checkEntityForHospital(
                id,
                authorizationHeader.getJwtToken(request));

        adminAndManagerService.deleteAllByHospitalId(id);
        return ResponseEntity.ok().body(ConstantResponseSuccessfulText.DELETE_TIMETABLE_FOR_HOSPITAL.formatted(id));
    }




    @PreAuthorize("isAuthenticated()")
    @GetMapping("/Hospital/{hospitalId:\\d+}")
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "Получение расписания больницы по Id", description = "Только авторизованные пользователи. Все поля обезательны")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ResponseTimetableDto.class)))),
            @ApiResponse(responseCode = "404", description = "Елси не будет надена больница, то вернётся ошибка", content = @Content(schema = @Schema(implementation = Validate.class)))
    })
    public ResponseEntity<List<ResponseTimetableDto>> getAllTimetableByHospitalId(@PathVariable(name = "hospitalId") Long id,
                                                                                  @RequestParam(name = "from") String from,
                                                                                  @RequestParam(name = "to") String to,
                                                                                 HttpServletRequest request) {
        microserviceEntityChecker.checkEntityForHospital(
                id,
                authorizationHeader.getJwtToken(request)
        );

        LocalDateTime fromDateTime = LocalDateTime.parse(from.replace("Z", ""));
        LocalDateTime toDateTime = LocalDateTime.parse(to.replace("Z", ""));

        return ResponseEntity.ok().body(
                authorizedUserService.getAllTimetableByHospitalId(fromDateTime, toDateTime, id)
        );
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/Doctor/{doctorId:\\d+}")
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "Получение расписания врача по Id", description = "Только авторизованные пользователи. Все поля обезательны")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ResponseTimetableDto.class)))),
            @ApiResponse(responseCode = "404", description = "Елси не будет наден доктор, то вернётся ошибка", content = @Content(schema = @Schema(implementation = Validate.class)))
    })
    public ResponseEntity<List<ResponseTimetableDto>> getAllTimetableByDoctorId(@PathVariable(name = "doctorId") Long id,
                                                                                @RequestParam(name = "from") String from,
                                                                                @RequestParam(name = "to") String to,
                                                                                HttpServletRequest request) {
        microserviceEntityChecker.checkEntityForUser(
                id,
                authorizationHeader.getJwtToken(request)
        );

        LocalDateTime fromDateTime = LocalDateTime.parse(from.replace("Z", ""));
        LocalDateTime toDateTime = LocalDateTime.parse(to.replace("Z", ""));

        return ResponseEntity.ok().body(
                authorizedUserService.getAllTimetableByDoctorById(fromDateTime, toDateTime, id)
        );
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_DOCTOR')")
    @GetMapping("/Hospital/{hospitalId:\\d+}/Room/{room}")
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "Получение расписания кабинета больницы", description = "Только администраторы и менеджерыи, и врачи")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ResponseTimetableDto.class)))),
            @ApiResponse(responseCode = "404", description = "Елси не будет надена больница или комната", content = @Content(schema = @Schema(implementation = Validate.class)))
    })
    public ResponseEntity<List<ResponseTimetableDto>> getAllTimetableByHospitalIdAndByRoom(
            @PathVariable(name = "hospitalId") Long id,
            @PathVariable(name = "room") String room,
            @RequestParam(name = "from") String from,
            @RequestParam(name = "to") String to,
            HttpServletRequest request) {

        microserviceEntityChecker.checkEntityForHospital(
                id,
                room,
              authorizationHeader.getJwtToken(request)
        );

        LocalDateTime fromDateTime = LocalDateTime.parse(from.replace("Z", ""));
        LocalDateTime toDateTime = LocalDateTime.parse(to.replace("Z", ""));

        return ResponseEntity.ok().body(
                authorizedUserService.getAllTimetableByHospitalIdAndByRoom(fromDateTime, toDateTime, room, id)
        );
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("{timetableId:\\d+}/Appointments")
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "Получение свободных талонов на приём.", description = "Только авторизованные пользователи")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ResponseAppointmentsDto.class)))),
            @ApiResponse(responseCode = "404", description = "Елси не будет надено расписание", content = @Content(schema = @Schema(implementation = Validate.class)))
    })
    public ResponseEntity<List<ResponseAppointmentsDto>> getAvailableAppointment(@PathVariable(name = "timetableId") Long id) {
        microserviceEntityChecker.checkEntityTimetable(id);
        return ResponseEntity.ok().body(
                authorizedUserService.getAllAvailableSlotsByIdTimetable(id)
        );
    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping("{timetableId:\\d+}/Appointments")
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "Записаться на приём.", description = "Только авторизованные пользователи")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(type = "sting", example = ConstantResponseSuccessfulText.UPDATE_APPOINTMENT_TRUE))),
            @ApiResponse(responseCode = "404", description = "Елси не будет надено расписание", content = @Content(schema = @Schema(implementation = Validate.class)))
    })
    public ResponseEntity<String> makeAppointment(@PathVariable(name = "timetableId") Long timetableId,
                                                  @RequestParam("time") LocalDateTime time,
                                                  HttpServletRequest request) {
        microserviceEntityChecker.checkEntityTimetable(timetableId);

        authorizedUserService.makeAppointment(
                time,
                timetableId,
                searchingFieldsBetweenMicroservicesUser.getUserId(authorizationHeader.getAuthorization(request)));

        return ResponseEntity.ok().body(
                ConstantResponseSuccessfulText.UPDATE_APPOINTMENT_TRUE.formatted(time.toString())
        );
    }
}
