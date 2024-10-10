package com.example.Document_microservice.convert.manager;

import com.example.Document_microservice.dto.RequestHistoryDto;
import com.example.Document_microservice.model.History;

public interface ManagerMapperHistory {

    History toModel(RequestHistoryDto dto);
    History toModel(RequestHistoryDto dto, Long historyId);
}