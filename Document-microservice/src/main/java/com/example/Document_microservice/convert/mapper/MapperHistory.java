package com.example.Document_microservice.convert.mapper;

import com.example.Document_microservice.dto.RequestHistoryDto;
import com.example.Document_microservice.model.History;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapperHistory {

    History toModel(RequestHistoryDto dto);
    History toModel(RequestHistoryDto dto, Long id);
}
