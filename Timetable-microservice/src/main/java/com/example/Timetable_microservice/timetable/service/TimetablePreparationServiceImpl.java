package com.example.Timetable_microservice.timetable.service;


import com.example.Timetable_microservice.appointment.service.AppointmentServiceFacade;
import com.example.Timetable_microservice.timetable.convert.manager.ManagerMapperTimetable;
import com.example.Timetable_microservice.timetable.dto.RequestTimetableDto;
import com.example.Timetable_microservice.timetable.model.Timetable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor

public class TimetablePreparationServiceImpl implements TimetablePreparationService {

    private final ManagerMapperTimetable mapperTimetable;
    private final AppointmentServiceFacade appointmentServiceFacade;




    @Override
    public Timetable build(RequestTimetableDto dto, Long id) {
        Timetable timetable = mapperTimetable.toModel(dto, id);
        timetable.setAppointments(
                appointmentServiceFacade.generateAppointments(
                        timetable.getFrom(),
                        timetable.getTo(),
                        timetable.getId()
        ));
        return timetable;
    }


}
