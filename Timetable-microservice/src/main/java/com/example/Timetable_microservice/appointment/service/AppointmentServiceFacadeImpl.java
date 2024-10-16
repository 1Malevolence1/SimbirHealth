package com.example.Timetable_microservice.appointment.service;


import com.example.Timetable_microservice.appointment.convert.manager.ManagerMapperAppointment;
import com.example.Timetable_microservice.appointment.dto.appointment.ResponseAppointmentsDto;
import com.example.Timetable_microservice.appointment.exception.RecordLockedByAnotherUserExceptionCustomer;
import com.example.Timetable_microservice.appointment.model.Appointment;
import com.example.Timetable_microservice.timetable.config.ConstantResponseExceptionText;
import com.example.Timetable_microservice.timetable.exception.Validate;
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
    public List<Appointment> generateAppointments(LocalDateTime from, LocalDateTime to, Long timetableId) {
        return mapperAppointment.toModel(
                appointmentSlotGenerator.generate(from, to, timetableId));
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
