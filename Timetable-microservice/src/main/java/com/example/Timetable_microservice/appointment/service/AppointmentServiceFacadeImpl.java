package com.example.Timetable_microservice.appointment.service;


import com.example.Timetable_microservice.appointment.convert.manager.ManagerMapperAppointment;
import com.example.Timetable_microservice.appointment.dto.appointment.ResponseAppointmentsDto;
import com.example.Timetable_microservice.appointment.model.Appointment;
import com.example.Timetable_microservice.timetable.service.AppointmentSlotGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AppointmentServiceFacadeImpl implements AppointmentServiceFacade {


    private final ManagerMapperAppointment mapperAppointment;
    private final AppointmentSlotGenerator appointmentSlotGenerator;
    private final AppointmentService appointmentService;


    @Override
    public List<Appointment> generateAppointments(LocalDateTime from, LocalDateTime to) {
        return mapperAppointment.toModel(
                appointmentSlotGenerator.generate(from, to));
    }

    @Override
    public List<ResponseAppointmentsDto> getAllAvailableSlots(Long id) {
        return mapperAppointment.toDto(appointmentService.fetchAvailableSlots(id));
    }
}
