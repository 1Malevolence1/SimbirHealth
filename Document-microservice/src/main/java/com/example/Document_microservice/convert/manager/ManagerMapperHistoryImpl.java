package com.example.Document_microservice.convert.manager;

import com.example.Document_microservice.convert.mapper.MapperHistory;
import com.example.Document_microservice.dto.RequestHistoryDto;
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
}
