package com.example.Document_microservice.repository;

import com.example.Document_microservice.model.History;
import org.springframework.data.jpa.repository.JpaRepository;


public interface HistoryRepository extends JpaRepository<History, Long> {
}
