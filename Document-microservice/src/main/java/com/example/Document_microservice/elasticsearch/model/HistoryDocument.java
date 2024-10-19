package com.example.Document_microservice.elasticsearch.model;


import com.example.Document_microservice.document.model.History;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Document(indexName = "history")
public class HistoryDocument {

    @Id
    private Long id;

    @Field(type = FieldType.Date, format = DateFormat.date)
    private LocalDateTime date;

    @Field(type = FieldType.Long )
    private Long pacientId;

    @Field(type = FieldType.Long)
    private Long hospitalId;

    @Field(type = FieldType.Long)
    private Long doctorId;

    @Field(type = FieldType.Text)
    private String data;

    @Field(type = FieldType.Text)
    private String room;


    public HistoryDocument(History history) {
        this.id = history.getId();
        this.date = history.getDate();
        this.pacientId = history.getPacientId();
        this.hospitalId = history.getHospitalId();
        this.doctorId = history.getDoctorId();
        this.data = history.getData();
        this.room = history.getRoom();
    }
}
