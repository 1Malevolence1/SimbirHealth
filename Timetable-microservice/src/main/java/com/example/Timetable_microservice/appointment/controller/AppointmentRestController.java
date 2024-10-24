package com.example.Timetable_microservice.appointment.controller;


import com.example.Timetable_microservice.timetable.config.ConstantResponseSuccessfulText;
import com.example.Timetable_microservice.timetable.exception.Validate;
import com.example.Timetable_microservice.timetable.service.authorized_user.AuthorizedUserService;
import com.example.Timetable_microservice.timetable.service.utils.AuthorizationHeaderExtractor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/Appointment/{appointmentId:\\d+}")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Appointment", description = "запросы начинающиеся с Appointment")
public class AppointmentRestController {
    private final AuthorizedUserService authorizedUserService;
    private final AuthorizationHeaderExtractor authorizationHeader;


    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_USER')")
    @DeleteMapping()
    @SecurityRequirement(name = "JWT")
    @Operation(summary = "Отменить запись на приём", description = "Только администраторы, менеджеры, и записавшийся пользователь")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(type = "sting", example = ConstantResponseSuccessfulText.UPDATE_APPOINTMENT_FALSE))),
            @ApiResponse(responseCode = "400", description = "Елси пользователь не записан на приём", content = @Content(schema = @Schema(implementation = Validate.class)))
    })
    public ResponseEntity<String> makeAppointment(@PathVariable(name = "appointmentId") Long appointmentId,
                                                  HttpServletRequest request) {

        authorizedUserService.cancelAppointment(
                authorizationHeader.getAuthorization(request),
                appointmentId
        );
        return ResponseEntity.ok().body(
                ConstantResponseSuccessfulText.UPDATE_APPOINTMENT_FALSE
        );
    }
}
