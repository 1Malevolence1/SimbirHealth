package com.example.Timetable_microservice.appointment.service;


import com.example.Timetable_microservice.appointment.convert.manager.ManagerMapperAppointment;
import com.example.Timetable_microservice.appointment.dto.appointment.ResponseAppointmentsDto;
import com.example.Timetable_microservice.appointment.exception.RecordLockedByAnotherUserExceptionCustomer;
import com.example.Timetable_microservice.appointment.model.Appointment;
import com.example.Timetable_microservice.timetable.config.ConstantResponseExceptionText;
import com.example.Timetable_microservice.timetable.exception.Validate;
import com.example.Timetable_microservice.timetable.model.Timetable;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class AppointmentServiceFacadeImpl implements AppointmentServiceFacade {


    private final ManagerMapperAppointment mapperAppointment;
    private final AppointmentSlotGenerator appointmentSlotGenerator;
    private final AppointmentService appointmentService;
    private final AppointmentCheck appointmentCheck;



    @Override
    public List<Appointment> generateAppointments(LocalDateTime from, LocalDateTime to, Timetable timetable) {
        return appointmentSlotGenerator.generate(from, to, timetable);
    }



    @Override
    public List<ResponseAppointmentsDto> getAllAvailableSlots(Long id) {
        return mapperAppointment.toDto(appointmentService.fetchAvailableSlots(id));
    }

    @Override
    public void makeAppointment(LocalDateTime time, Long timetableId,  Long userId) {
        log.info("{}, {}, {}", time, timetableId, userId);
        appointmentService.updateActiveOnTrue(time, timetableId, userId);
    }

    @Override
    @SneakyThrows
    public void cancelAppointment(Long userId, Long appointmentId) {
        log.info("{},{}", userId, appointmentId);
        Long recordingUserId = appointmentService.getUserIdFromAppointment(appointmentId);
        if(!Objects.equals(userId, recordingUserId)) throw new RecordLockedByAnotherUserExceptionCustomer(
                new Validate(
                        ConstantResponseExceptionText.BADE_DELETE_REGISTERED_BY_ID)
        );
        if(!appointmentCheck.checkUserIdInAppointment(appointmentId)) throw  new NoSuchElementException(ConstantResponseExceptionText.USER_NOT_REGISTERED_BY_ID.formatted(userId));
        appointmentService.updateActiveOnFalse(userId, appointmentId);
    }
}
