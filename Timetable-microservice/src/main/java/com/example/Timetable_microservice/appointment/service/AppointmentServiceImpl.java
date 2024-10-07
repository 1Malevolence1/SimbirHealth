package com.example.Timetable_microservice.appointment.service;

import com.example.Timetable_microservice.appointment.model.Appointment;
import com.example.Timetable_microservice.appointment.repository.AppointmentRepository;
import com.example.Timetable_microservice.timetable.config.ConstantResponseExceptionText;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
@Slf4j
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @Override
    public List<Appointment> fetchAvailableSlots(Long id) {
        return appointmentRepository.findAllAvailableSlots(id);
    }

    @Override
    public boolean findUserIdFromAppointment(Long appointmentId) {
        Long id = appointmentRepository.retrieveUserIdFromAppointment(appointmentId);
        return id != null;
    }

    @Override
    @Transactional
    public void updateActiveOnTrue(LocalDateTime time, Long userId) {
        appointmentRepository.updateSlotActiveToTrue(time, userId);
    }

    @Override
    @Transactional
    public void updateActiveOnFalse(Long userId, Long appointmentId) {
        appointmentRepository.updateSlotActiveToFalse(userId, appointmentId);
    }
}

