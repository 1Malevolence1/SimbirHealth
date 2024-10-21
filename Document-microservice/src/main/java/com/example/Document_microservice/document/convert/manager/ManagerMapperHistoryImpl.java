package com.example.Document_microservice.document.convert.manager;

import com.example.Document_microservice.document.convert.mapper.MapperHistory;
import com.example.Document_microservice.document.convert.mapper.MapperListHistory;
import com.example.Document_microservice.document.dto.RequestHistoryDto;
import com.example.Document_microservice.document.dto.ResponseHistoryDto;
import com.example.Document_microservice.document.elasticsearch.model.HistoryDocument;
import com.example.Document_microservice.document.model.History;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ManagerMapperHistoryImpl implements ManagerMapperHistory {
    private final MapperHistory mapperHistory;
    private final MapperListHistory mapperListHistory;

    @Override
    public History toModel(RequestHistoryDto dto) {
        return mapperHistory.toModel(dto);
    }

    @Override
    public History toModel(RequestHistoryDto dto, Long historyId) {
        return mapperHistory.toModel(dto, historyId);
    }

    @Override
    public ResponseHistoryDto toDto(History history) {
        return mapperHistory.toDto(history);
    }

    @Override
    public ResponseHistoryDto toDtoFromHistoryDocument(HistoryDocument history) {
       return mapperHistory.toDtoFromHistoryDocument(history);
    }


    @Override
    public List<ResponseHistoryDto> toDto(List<History> listHistory) {
        return mapperListHistory.toDto(listHistory);
    }

    @Override
    public Iterable<ResponseHistoryDto> toDtoFromHistoryDocument(Iterable<HistoryDocument> listHistory) {
        return mapperListHistory.toDtoFromHistoryDocument(listHistory);
    }

    @Override
    public HistoryDocument toModelHistoryInModelHistoryDocument(History history) {
        return mapperHistory.toModelHistoryInModelHistoryDocument(history);
    }


}
