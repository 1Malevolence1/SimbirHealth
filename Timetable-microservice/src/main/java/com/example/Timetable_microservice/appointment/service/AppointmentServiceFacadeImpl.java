package com.example.Timetable_microservice.appointment.service;


import com.example.Timetable_microservice.appointment.convert.manager.ManagerMapperAppointment;
import com.example.Timetable_microservice.appointment.dto.appointment.ResponseAppointmentsDto;
import com.example.Timetable_microservice.appointment.model.Appointment;
import com.example.Timetable_microservice.timetable.config.ConstantResponseExceptionText;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
@Slf4j
public class AppointmentServiceFacadeImpl implements AppointmentServiceFacade {


    private final ManagerMapperAppointment mapperAppointment;
    private final AppointmentSlotGenerator appointmentSlotGenerator;
    private final AppointmentService appointmentService;
    private final AppointmentCheck appointmentCheck;



    @Override
    public List<Appointment> generateAppointments(LocalDateTime from, LocalDateTime to) {
        return mapperAppointment.toModel(
                appointmentSlotGenerator.generate(from, to));
    }

    @Override
    public List<ResponseAppointmentsDto> getAllAvailableSlots(Long id) {
        return mapperAppointment.toDto(appointmentService.fetchAvailableSlots(id));
    }

    @Override
    public void makeAppointment(LocalDateTime time, Long id) {
        log.info("{}, {}", time, id);
        appointmentService.updateActiveOnTrue(time, id);
    }

    @Override
    public void cancelAppointment(Long userId, Long appointmentId) {
        log.info("{},{}", userId, appointmentId);
        if(!appointmentCheck.checkUserIdInAppointment(appointmentId)) throw  new NoSuchElementException(ConstantResponseExceptionText.USER_NOT_REGISTERED_BY_ID.formatted(userId));
        appointmentService.updateActiveOnFalse(userId, appointmentId);
    }
}
