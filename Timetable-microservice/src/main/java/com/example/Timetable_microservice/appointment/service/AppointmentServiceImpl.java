package com.example.Timetable_microservice.appointment.service;

import com.example.Timetable_microservice.appointment.model.Appointment;
import com.example.Timetable_microservice.appointment.repository.AppointmentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


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
    @Transactional
    public void updateActiveOnTrue(LocalDateTime time, Long userId) {
        appointmentRepository.updateSlotActiveToTrue(time, userId);
    }

    @Override
    @Transactional
    public void updateActiveOnFalse(LocalDateTime time, Long userId) {
        appointmentRepository.updateSlotActiveToFalse(time, userId);
    }
}

