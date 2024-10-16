package com.example.Timetable_microservice.appointment.service;

import com.example.Timetable_microservice.appointment.dto.appointment.AppointmentDto;
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
    public List<AppointmentDto> generate(LocalDateTime from, LocalDateTime to, Long timetableId) {
        List<AppointmentDto> appointmentDtoList = new ArrayList<>();
        LocalDateTime slot = from;
        while (slot.isBefore(to)) {
            appointmentDtoList.add(
                    new AppointmentDto(
                            slot,
                            false,
                            timetableId
                    )
            );
            slot = slot.plusMinutes(30);
        }
        return appointmentDtoList;
    }
}
