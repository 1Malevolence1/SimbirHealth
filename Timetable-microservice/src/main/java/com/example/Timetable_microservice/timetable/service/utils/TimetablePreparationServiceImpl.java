package com.example.Timetable_microservice.timetable.service.utils;


import com.example.Timetable_microservice.appointment.model.Appointment;
import com.example.Timetable_microservice.appointment.service.AppointmentServiceFacade;
import com.example.Timetable_microservice.timetable.convert.manager.ManagerMapperTimetable;
import com.example.Timetable_microservice.timetable.dto.timetable.RequestTimetableDto;
import com.example.Timetable_microservice.timetable.model.Timetable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TimetablePreparationServiceImpl implements TimetablePreparationService {

    private final ManagerMapperTimetable mapperTimetable;
    private final AppointmentServiceFacade appointmentServiceFacade;

    @Override
    public Timetable build(RequestTimetableDto dto) {
        Timetable timetable = mapperTimetable.toModel(dto);
        List<Appointment> appointments = appointmentServiceFacade.generateAppointments((timetable.getFrom()), timetable.getTo(), timetable);
        timetable.setAppointments(appointments);
        return timetable;
    }

    @Override
    public Timetable build(RequestTimetableDto dto, Long timetableId) {
        Timetable timetable = mapperTimetable.toModel(dto, timetableId);
        List<Appointment> appointments = appointmentServiceFacade.generateAppointments((timetable.getFrom()), timetable.getTo(), timetable);
        timetable.setAppointments(appointments);
        return timetable;
    }
}
