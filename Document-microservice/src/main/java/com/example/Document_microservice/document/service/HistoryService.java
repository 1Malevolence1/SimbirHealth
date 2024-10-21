package com.example.Document_microservice.document.service;

import com.example.Document_microservice.document.model.History;

public interface HistoryService {

    History save(History history);
    void update(History history);
    History findById(Long id);
}
