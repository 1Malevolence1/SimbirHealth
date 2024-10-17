package com.example.Document_microservice.document.convert.mapper;

import com.example.Document_microservice.document.dto.RequestHistoryDto;
import com.example.Document_microservice.document.dto.ResponseHistoryDto;
import com.example.Document_microservice.document.model.History;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapperHistory {

    History toModel(RequestHistoryDto dto);
    History toModel(RequestHistoryDto dto, Long id);
    ResponseHistoryDto toDto(History model);
}
