package com.example.Document_microservice.document.repository;

import com.example.Document_microservice.document.model.History;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface HistoryRepository extends JpaRepository<History, Long> {

    List<History> findAllByPacientId(Long pacientId);
}
