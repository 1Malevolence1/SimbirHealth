package com.example.Document_microservice.service;

import com.example.Document_microservice.model.History;

import java.util.List;

public interface HistoryService {

    void save(History history);
    void update(History history);
    History getHistoryById(Long historyId);
    List<History> getAllHistoryByPacientId(Long pacientId);
}
