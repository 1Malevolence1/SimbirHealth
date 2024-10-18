package com.example.Document_microservice.elasticsearch.repository;


import com.example.Document_microservice.elasticsearch.model.HistoryDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryElasticsearchRepository extends ElasticsearchRepository<HistoryDocument, Long > {
}
