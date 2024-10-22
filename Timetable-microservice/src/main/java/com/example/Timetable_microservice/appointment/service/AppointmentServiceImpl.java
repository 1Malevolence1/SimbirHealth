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
    public boolean findUserIdFromAppointment(Long appointmentId) {
        Long id = appointmentRepository.retrieveUserIdFromAppointment(appointmentId);
        return id != null;
    }

    @Override
    public Long getUserIdInAppointmentTime(Long timetabled, LocalDateTime date) {
        return appointmentRepository.userIdInData(timetabled, date);
    }

    @Override
    public Long getCountUserSignedUpForAppointment(Long timetableId) {
        return appointmentRepository.retrieveCountUserIdFromAppointment(timetableId);
    }

    @Override
    public Long getUserIdFromAppointment(Long appointmentId) {
        return appointmentRepository.retrieveUserIdFromAppointment(appointmentId);
    }

    @Override
    @Transactional
    public void updateActiveOnTrue(LocalDateTime time, Long timetableId, Long userId) {
        log.info("{}, {}, {}", time, timetableId, userId);
        appointmentRepository.updateSlotActiveToTrue(time, timetableId, userId);
    }

    @Override
    @Transactional
    public void updateActiveOnFalse(Long userId, Long appointmentId) {
        appointmentRepository.updateSlotActiveToFalse(userId, appointmentId);
    }

    @Override
    @Transactional
    public void updateActiveOnFalse(Long appointmentId) {
        appointmentRepository.updateSlotActiveToFalse(appointmentId);
    }

    @Override
    @Transactional
    public void deleteAllAppointmentByIdTimetable(Long timetable) {
        appointmentRepository.deleteAllByIdTimetable(timetable);
    }

    @Override
    @Transactional
    public void saveAppointments(List<Appointment> appointments) {
        appointmentRepository.saveAll(appointments);
    }
}

