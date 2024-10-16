package com.example.Timetable_microservice.appointment.service;

import com.example.Timetable_microservice.appointment.model.Appointment;
import com.example.Timetable_microservice.timetable.model.Timetable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Component
@RequiredArgsConstructor
@Slf4j
public class AppointmentSlotGeneratorImpl implements AppointmentSlotGenerator {

    @Override
    public List<Appointment> generate(LocalDateTime from, LocalDateTime to, Timetable timetable) {
        List<Appointment> appointmentDtoList = new ArrayList<>();
        LocalDateTime slot = from;
        while (slot.isBefore(to)) {
            appointmentDtoList.add(
                    new Appointment(
                            null,
                            slot,
                            false,
                            null,
                            timetable
                    )
            );
            slot = slot.plusMinutes(30);
        }
        return appointmentDtoList;
    }
}
