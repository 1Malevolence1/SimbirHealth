package com.example.Document_microservice.document.convert.manager;

import com.example.Document_microservice.document.dto.RequestHistoryDto;
import com.example.Document_microservice.document.dto.ResponseHistoryDto;
import com.example.Document_microservice.document.elasticsearch.model.HistoryDocument;
import com.example.Document_microservice.document.model.History;

import java.util.List;

public interface ManagerMapperHistory {

    History toModel(RequestHistoryDto dto);
    History toModel(RequestHistoryDto dto, Long historyId);
    ResponseHistoryDto toDto(History history);
    ResponseHistoryDto toDtoFromHistoryDocument(HistoryDocument history);

    List<ResponseHistoryDto> toDto(List<History> listHistory);
    Iterable<ResponseHistoryDto> toDtoFromHistoryDocument(Iterable<HistoryDocument> listHistory);
    HistoryDocument toModelHistoryInModelHistoryDocument(History history);
}
