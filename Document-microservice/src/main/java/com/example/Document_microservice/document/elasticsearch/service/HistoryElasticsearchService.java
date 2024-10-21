package com.example.Document_microservice.document.elasticsearch.service;

import com.example.Document_microservice.document.elasticsearch.model.HistoryDocument;

public interface HistoryElasticsearchService {

    Iterable<HistoryDocument> getAllHistoryByPacientId(Long id);
    HistoryDocument findHistoryById(Long id);
    void add(HistoryDocument historyDocument);

    void update(HistoryDocument historyDocument);
}
