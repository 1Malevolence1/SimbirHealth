package com.example.Timetable_microservice.appointment.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "appointment")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime recording;

    private Boolean active;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "timetable_id")
    private Long timetableId;

}
