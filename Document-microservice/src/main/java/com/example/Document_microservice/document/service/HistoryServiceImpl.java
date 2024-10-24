package com.example.Document_microservice.document.service;

import com.example.Document_microservice.document.config.ConstantResponseExceptionText;
import com.example.Document_microservice.document.model.History;
import com.example.Document_microservice.document.repository.HistoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
@Slf4j
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository historyRepository;

    @Override
    @Transactional
    public History save(History history) {
        return historyRepository.save(history);
    }

    @Override
    @Transactional
    public void update(History updateHistory) {
        historyRepository.findById(updateHistory.getId()).ifPresentOrElse(
                history -> {
                    if(updateHistory.getData() != null) history.setData(updateHistory.getData());
                    if(updateHistory.getDate() != null) history.setDate(updateHistory.getDate());
                    if(updateHistory.getDoctorId() != null) history.setDoctorId(updateHistory.getDoctorId());
                    if(updateHistory.getPacientId() != null) history.setPacientId(updateHistory.getPacientId());
                    if(updateHistory.getHospitalId() != null) history.setHospitalId(updateHistory.getHospitalId());
                }, () -> {
                    throw new NoSuchElementException(ConstantResponseExceptionText.NOT_FOUND_HISTORY_BY_ID.formatted(updateHistory.getId()));
                }
        );
    }

    @Override
    public History findById(Long id) {
        return historyRepository.findById(id).orElseThrow(() -> new NoSuchElementException(ConstantResponseExceptionText.NOT_FOUND_HISTORY_BY_ID.formatted(id)));
    }
}
