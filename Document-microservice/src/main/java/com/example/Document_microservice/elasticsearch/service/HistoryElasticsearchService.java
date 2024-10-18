package com.example.Document_microservice.elasticsearch.service;

import com.example.Document_microservice.elasticsearch.model.HistoryDocument;

public interface HistoryElasticsearchService {

    Iterable<HistoryDocument> getAllHistory();
}
