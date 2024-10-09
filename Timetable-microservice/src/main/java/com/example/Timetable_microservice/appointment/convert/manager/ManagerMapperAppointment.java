package com.example.Timetable_microservice.appointment.convert.manager;

import com.example.Timetable_microservice.appointment.dto.appointment.AppointmentDto;
import com.example.Timetable_microservice.appointment.dto.appointment.ResponseAppointmentsDto;
import com.example.Timetable_microservice.appointment.model.Appointment;

import java.util.List;

public interface ManagerMapperAppointment {

    List<Appointment> toModel(List<AppointmentDto> listDto);
    List<ResponseAppointmentsDto> toDto(List<Appointment> listModel);
}
