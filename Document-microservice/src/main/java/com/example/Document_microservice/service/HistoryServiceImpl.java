package com.example.Document_microservice.service;

import com.example.Document_microservice.model.History;
import com.example.Document_microservice.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository historyRepository;

    @Override
    public void save(History history) {
        historyRepository.save(history);
    }
}
