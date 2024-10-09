package com.example.Document_microservice.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class History {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date;


    @Column(name = "pacient_id")
    private Long pacientId;

    @Column(name = "hospital_id")
    private Long hospitalId;

    @Column(name = "doctor_id")
    private Long doctorId;


    private String data;

}
