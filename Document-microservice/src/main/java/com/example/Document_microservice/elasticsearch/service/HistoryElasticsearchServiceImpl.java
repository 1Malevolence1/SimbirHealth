package com.example.Document_microservice.elasticsearch.service;

import com.example.Document_microservice.elasticsearch.model.HistoryDocument;
import com.example.Document_microservice.elasticsearch.repository.HistoryElasticsearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HistoryElasticsearchServiceImpl implements HistoryElasticsearchService {

    private final HistoryElasticsearchRepository historyElasticsearchRepository;

    @Override
    public Iterable<HistoryDocument> getAllHistory() {
        return historyElasticsearchRepository.findAll();
    }
}
