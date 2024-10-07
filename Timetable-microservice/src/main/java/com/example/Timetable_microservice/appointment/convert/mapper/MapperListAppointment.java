package com.example.Timetable_microservice.appointment.convert.mapper;


import com.example.Timetable_microservice.appointment.dto.appointment.AppointmentDto;
import com.example.Timetable_microservice.appointment.dto.appointment.ResponseAppointmentsDto;
import com.example.Timetable_microservice.appointment.model.Appointment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = MapperAppointment.class)
public interface MapperListAppointment {

    List<Appointment> toModel(List<AppointmentDto> listDto);
    List<ResponseAppointmentsDto> toDto(List<Appointment> listModel);
}
