package com.example.Document_microservice.document.elasticsearch.service;

import com.example.Document_microservice.document.config.ConstantResponseExceptionText;
import com.example.Document_microservice.document.elasticsearch.model.HistoryDocument;
import com.example.Document_microservice.document.elasticsearch.repository.HistoryElasticsearchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
public class HistoryElasticsearchServiceImpl implements HistoryElasticsearchService {

    private final HistoryElasticsearchRepository historyElasticsearchRepository;

    @Override
    public Iterable<HistoryDocument> getAllHistoryByPacientId(Long id) {
        Iterable<HistoryDocument> historyDocuments = historyElasticsearchRepository.findByPacientId(id);
        log.info("{}", historyDocuments);
        return historyDocuments;
    }

    @Override
    public HistoryDocument findHistoryById(Long historyId) {
        return historyElasticsearchRepository.findById(historyId).orElseThrow(() -> new NoSuchElementException(ConstantResponseExceptionText.NOT_FOUND_HISTORY_BY_ID.formatted(historyId)));
    }

    @Override
    public void add(HistoryDocument historyDocument) {
        log.info("{}", historyDocument);
        historyElasticsearchRepository.save(historyDocument);
    }

    @Override
    public void update(HistoryDocument updateHistoryDocument) {
        historyElasticsearchRepository.save(updateHistoryDocument);
    }
}
