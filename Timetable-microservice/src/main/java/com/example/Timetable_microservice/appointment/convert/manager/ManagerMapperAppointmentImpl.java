package com.example.Timetable_microservice.appointment.convert.manager;

import com.example.Timetable_microservice.appointment.convert.mapper.MapperListAppointment;
import com.example.Timetable_microservice.appointment.dto.appointment.AppointmentDto;
import com.example.Timetable_microservice.appointment.dto.appointment.ResponseAppointmentsDto;
import com.example.Timetable_microservice.appointment.model.Appointment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class ManagerMapperAppointmentImpl implements ManagerMapperAppointment {

    private final MapperListAppointment mapperListAppointment;
    @Override
    public List<Appointment> toModel(List<AppointmentDto> listDto) {
        return mapperListAppointment.toModel(listDto);
    }

    @Override
    public List<ResponseAppointmentsDto> toDto(List<Appointment> listModel) {
        return mapperListAppointment.toDto(listModel);
    }
}
