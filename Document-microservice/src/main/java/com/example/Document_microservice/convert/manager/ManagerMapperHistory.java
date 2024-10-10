package com.example.Document_microservice.convert.manager;

import com.example.Document_microservice.dto.RequestHistoryDto;
import com.example.Document_microservice.dto.ResponseHistoryDto;
import com.example.Document_microservice.model.History;

import java.util.List;

public interface ManagerMapperHistory {

    History toModel(RequestHistoryDto dto);
    History toModel(RequestHistoryDto dto, Long historyId);
    ResponseHistoryDto toDto(History history);
    List<ResponseHistoryDto> toDto(List<History> listHistory);
}
