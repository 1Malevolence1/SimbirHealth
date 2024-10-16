package com.example.Timetable_microservice.appointment.controller;


import com.example.Timetable_microservice.timetable.config.ConstantResponseSuccessfulText;
import com.example.Timetable_microservice.timetable.exception.Validate;
import com.example.Timetable_microservice.timetable.service.authorized_user.AuthorizedUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/Appointment/{appointmentId:\\d+}")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Appointment", description = "запросы начинающиеся с Appointment")
public class AppointmentRestController {
    private final AuthorizedUserService authorizedUserService;


    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_USER')")
    @DeleteMapping()
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "ЗОтменить запись на приём", description = "Только администраторы, менеджеры, и записавшийся пользователь")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(type = "sting", example = ConstantResponseSuccessfulText.UPDATE_APPOINTMENT_FALSE))),
            @ApiResponse(responseCode = "400", description = "Елси пользователь не записан на приём", content = @Content(schema = @Schema(implementation = Validate.class)))
    })
    public ResponseEntity<String> makeAppointment(@PathVariable(name = "appointmentId") Long appointmentId,
                                                  @RequestHeader("Authorization") String authorizationHeader) {

        authorizedUserService.cancelAppointment(
                authorizationHeader,
                appointmentId
        );
        return ResponseEntity.ok().body(
                ConstantResponseSuccessfulText.UPDATE_APPOINTMENT_FALSE
        );
    }
}
