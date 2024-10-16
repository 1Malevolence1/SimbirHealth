package com.example.Document_microservice.document.service;

import com.example.Document_microservice.document.config.ConstantResponseExceptionText;
import com.example.Document_microservice.document.model.History;
import com.example.Document_microservice.document.repository.HistoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
@Slf4j
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository historyRepository;

    @Override
    @Transactional
    public void save(History history) {
        historyRepository.save(history);
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
    public History getHistoryById(Long historyId) {
       return historyRepository.findById(historyId).orElseThrow(() -> new NoSuchElementException(ConstantResponseExceptionText.NOT_FOUND_HISTORY_BY_ID.formatted(historyId)));
    }

    @Override
    public List<History> getAllHistoryByPacientId(Long pacientId) {
        return historyRepository.findAllByPacientId(pacientId);
    }
}
