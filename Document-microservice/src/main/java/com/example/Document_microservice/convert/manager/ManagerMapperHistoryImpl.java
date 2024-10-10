package com.example.Document_microservice.convert.manager;

import com.example.Document_microservice.convert.mapper.MapperHistory;
import com.example.Document_microservice.dto.RequestHistoryDto;
import com.example.Document_microservice.dto.ResponseHistoryDto;
import com.example.Document_microservice.model.History;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ManagerMapperHistoryImpl implements ManagerMapperHistory {
    private final MapperHistory mapperHistory;
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
}
