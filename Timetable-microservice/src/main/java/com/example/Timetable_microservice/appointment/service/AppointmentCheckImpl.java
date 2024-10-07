package com.example.Timetable_microservice.appointment.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppointmentCheckImpl implements AppointmentCheck {

    private final AppointmentService appointmentService;

    @Override
    public boolean checkUserIdInAppointment(Long appId) {
       return appointmentService.findUserIdFromAppointment(appId);
    }
}
