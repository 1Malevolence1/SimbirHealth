package com.example.Document_microservice.document.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "history")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@ToString
public class History {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private Long id;
    @Column(name = "history_date", nullable = false)
    private LocalDateTime date;


    @Column(name = "pacient_id", nullable = false)
    private Long pacientId;

    @Column(name = "hospital_id", nullable = false)
    private Long hospitalId;

    @Column(name = "doctor_id", nullable = false)
    private Long doctorId;

    @Column(nullable = false)
    private String data;

    @Column(name = "room", nullable = false)
    private String room;

}
