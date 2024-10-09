package com.example.Timetable_microservice.appointment.convert.mapper;

import com.example.Timetable_microservice.appointment.dto.appointment.AppointmentDto;
import com.example.Timetable_microservice.appointment.dto.appointment.ResponseAppointmentsDto;
import com.example.Timetable_microservice.appointment.model.Appointment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapperAppointment {

    Appointment toModel(AppointmentDto appointmentDto);
    ResponseAppointmentsDto toDto(Appointment appointmentModel);
}
