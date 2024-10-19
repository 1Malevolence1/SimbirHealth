package com.example.Document_microservice.elasticsearch.service;

import com.example.Document_microservice.elasticsearch.model.HistoryDocument;
import com.example.Document_microservice.elasticsearch.repository.HistoryElasticsearchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class HistoryElasticsearchServiceImpl implements HistoryElasticsearchService {

    private final HistoryElasticsearchRepository historyElasticsearchRepository;

    @Override
    public Iterable<HistoryDocument> getAllHistory() {
        Iterable<HistoryDocument> historyDocuments = historyElasticsearchRepository.findAll();
        log.info("{}", historyDocuments);
        return historyDocuments;
    }
}
