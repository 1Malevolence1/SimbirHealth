package com.example.Timetable_microservice.timetable.service.AdminAndManagerService;


import com.example.Timetable_microservice.appointment.service.AppointmentService;
import com.example.Timetable_microservice.timetable.config.ConstantResponseExceptionText;
import com.example.Timetable_microservice.timetable.dto.RequestTimetableDto;
import com.example.Timetable_microservice.timetable.exception.BadUpdateTimetable;
import com.example.Timetable_microservice.timetable.exception.Validate;
import com.example.Timetable_microservice.timetable.service.TimetablePreparationService;
import com.example.Timetable_microservice.timetable.service.TimetableService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminAndManagerServiceImpl implements AdminAndManagerService {

    private final TimetableService timetableService;
    private final AppointmentService appointmentService;
    private final TimetablePreparationService timetablePreparationService;

    @Override
    public void add(RequestTimetableDto dto) {
        timetableService.save(
                timetablePreparationService.build(dto)
        );
    }

    @Override
    public void update(RequestTimetableDto dto, Long timetableId) {
        if(appointmentService.getCountUserSignedUpForAppointment(timetableId) != 0) throw new BadUpdateTimetable(
                new Validate(ConstantResponseExceptionText.BAD_UPDATE_TIMETABLE_BY_ID
                ));
        appointmentService.deleteAllAppointmentByIdTimetable(timetableId);
        timetableService.update(
                timetablePreparationService.build(
                        dto, timetableId
                )
        );
    }

    @Override
    public void deleteById(Long id) {
        timetableService.deleteById(id);
    }

    @Override
    public void deleteAllByDoctorId(Long id) {
        timetableService.deleteAllByDoctorId(id);
    }

    @Override
    public void deleteAllByHospitalId(Long id) {
        timetableService.deleteAllByHospitalId(id);
    }

    @Override
    public void cancelAppointment(Long appointmentId) {
            appointmentService.updateActiveOnFalse(appointmentId);
    }
}
