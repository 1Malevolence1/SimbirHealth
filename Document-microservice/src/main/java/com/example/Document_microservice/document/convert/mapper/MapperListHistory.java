package com.example.Document_microservice.document.convert.mapper;


import com.example.Document_microservice.document.dto.ResponseHistoryDto;
import com.example.Document_microservice.document.elasticsearch.model.HistoryDocument;
import com.example.Document_microservice.document.model.History;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = MapperHistory.class)
public interface MapperListHistory {

    List<ResponseHistoryDto> toDto(List<History> listModel);
    List<ResponseHistoryDto> toDtoFromHistoryDocument(Iterable<HistoryDocument> listModel);
}
