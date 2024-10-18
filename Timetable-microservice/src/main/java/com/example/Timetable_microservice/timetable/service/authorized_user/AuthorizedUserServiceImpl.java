package com.example.Timetable_microservice.timetable.service.authorized_user;


import com.example.Timetable_microservice.appointment.dto.appointment.ResponseAppointmentsDto;
import com.example.Timetable_microservice.appointment.service.AppointmentServiceFacade;
import com.example.Timetable_microservice.timetable.convert.manager.ManagerMapperTimetable;
import com.example.Timetable_microservice.timetable.dto.timetable.ResponseTimetableDto;
import com.example.Timetable_microservice.timetable.service.AdminAndManagerService.AdminAndManagerService;
import com.example.Timetable_microservice.timetable.service.timetable.TimetableService;
import com.example.Timetable_microservice.timetable.service.utils.SearchingFieldsBetweenMicroservicesUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorizedUserServiceImpl implements AuthorizedUserService {

    private final TimetableService timetableService;
    private final ManagerMapperTimetable mapperTimetable;
    private final AppointmentServiceFacade appointmentServiceFacade;
    private final SearchingFieldsBetweenMicroservicesUser searchingFieldsBetweenMicroservicesUser;
    private final AdminAndManagerService adminAndManagerService;

    @Override
    public List<ResponseTimetableDto> getAllTimetableByHospitalId(LocalDateTime from, LocalDateTime to, Long id) {
        return mapperTimetable.toDto(
                timetableService.getAllTimetableWithParamsFromAndToByHospitalId(from, to, id)
        );
    }

    @Override
    public List<ResponseTimetableDto> getAllTimetableByHospitalIdAndByRoom(LocalDateTime from, LocalDateTime to, String room, Long id) {
        return mapperTimetable.toDto(
                timetableService.getAllTimetableWithParamsFromAndToByHospitalIdAndRoom(from, to, room, id)
        );
    }

    @Override
    public List<ResponseTimetableDto> getAllTimetableByDoctorById(LocalDateTime from, LocalDateTime to, Long id) {
        return mapperTimetable.toDto(
                timetableService.getAllTimetableWithParamsFromAndToByDoctorId(from, to, id)
        );
    }

    @Override
    public List<ResponseAppointmentsDto> getAllAvailableSlotsByIdTimetable(Long id) {
        return appointmentServiceFacade.getAllAvailableSlots(id);
    }

    @Override
    public void makeAppointment(LocalDateTime time, Long timetableId, Long userId) {
         appointmentServiceFacade.makeAppointment(time, timetableId, userId);
    }

    @Override
    public void cancelAppointment(String token, Long appointmentId) {
            String role = searchingFieldsBetweenMicroservicesUser.getRole(token);

            if(role.equals("ROLE_USER")) {
                appointmentServiceFacade.cancelAppointment(
                        searchingFieldsBetweenMicroservicesUser.getUserId(token),
                        appointmentId);
            } else {
                adminAndManagerService.cancelAppointment(appointmentId);
            }
    }
}
