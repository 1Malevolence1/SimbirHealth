    package com.example.Timetable_microservice.timetable.service;

    import com.example.Timetable_microservice.timetable.config.ConstantResponseExceptionText;
    import com.example.Timetable_microservice.timetable.model.Timetable;
    import com.example.Timetable_microservice.timetable.repostiory.TimetableRepository;
    import jakarta.transaction.Transactional;
    import lombok.RequiredArgsConstructor;
    import lombok.extern.slf4j.Slf4j;
    import org.springframework.stereotype.Service;

    import java.time.LocalDateTime;
    import java.util.List;
    import java.util.NoSuchElementException;


    @Service
    @RequiredArgsConstructor
    @Slf4j
    public class TimetableServiceImpl implements TimetableService {

        private final TimetableRepository timetableRepository;

        @Override
        @Transactional
        public void save(Timetable timetable) {
            timetableRepository.save(timetable);
        }

        @Override
        @Transactional
        public void update(Timetable timetableUpdate) {
            timetableRepository.findById(timetableUpdate.getId()).ifPresentOrElse(
                    timetable -> {
                        if (timetableUpdate.getHospitalId() != null)
                            timetable.setHospitalId(timetableUpdate.getHospitalId());
                        if (timetableUpdate.getDoctorId() != null) timetable.setDoctorId(timetableUpdate.getDoctorId());
                        if (timetableUpdate.getRoom() != null) timetable.setRoom(timetableUpdate.getRoom());
                        if (timetableUpdate.getFrom() != null) timetable.setFrom(timetableUpdate.getFrom());
                        if (timetableUpdate.getTo() != null) timetable.setTo(timetableUpdate.getTo());
                    }, () -> {
                        throw new NoSuchElementException(ConstantResponseExceptionText.NOT_FOUND_TIMETABLE_BY_ID.formatted(timetableUpdate.getHospitalId()));
                    }
            );
        }

        @Override
        public void deleteById(Long id) {
            timetableRepository.deleteById(id);
        }

        @Override
        @Transactional
        public void deleteAllByDoctorId(Long id) {
            timetableRepository.deleteAllByDoctorId(id);
        }

        @Override
        public List<Timetable> getAllTimetableWithParamsFromAndToByHospitalId(LocalDateTime from, LocalDateTime to, Long id) {
            return timetableRepository.getAllTimetableWithParamsFromAndToByHospitalId(from, to, id);
        }

        @Override
        public List<Timetable> getAllTimetableWithParamsFromAndToByDoctorId(LocalDateTime from, LocalDateTime to, Long id) {
            return timetableRepository.getAllTimetableWithParamsFromAndToByDoctorId(from, to, id);
        }


        @Override
        @Transactional
        public void deleteAllByHospitalId(Long id) {
            timetableRepository.deleteAllByHospitalId(id);
        }
    }
