package com.example.Timetable_microservice.appointment.controller;


import com.example.Timetable_microservice.timetable.config.ConstantResponseSuccessfulText;
import com.example.Timetable_microservice.timetable.service.authorized_user.AuthorizedUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/Appointment/{appointmentId:\\d+}")
@RequiredArgsConstructor
@Slf4j
public class AppointmentRestController {
    private final AuthorizedUserService authorizedUserService;


    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_USER')")
    @DeleteMapping()
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
