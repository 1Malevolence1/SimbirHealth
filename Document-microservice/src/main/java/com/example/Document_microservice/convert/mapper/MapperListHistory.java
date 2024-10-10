package com.example.Document_microservice.convert.mapper;


import com.example.Document_microservice.dto.ResponseHistoryDto;
import com.example.Document_microservice.model.History;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = MapperHistory.class)
public interface MapperListHistory {

    List<ResponseHistoryDto> toDto(List<History> listModel);
}
