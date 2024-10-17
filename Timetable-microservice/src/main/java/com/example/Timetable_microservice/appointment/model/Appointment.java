package com.example.Timetable_microservice.appointment.model;


import com.example.Timetable_microservice.timetable.model.Timetable;
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
    @Column(name = "appointment_id")
    private Long id;

    private LocalDateTime recording;

    private Boolean active;

    @Column(name = "user_id")
    private Long userId;

    @ManyToOne // Связь с Timetable
    @JoinColumn(name = "timetable_id", nullable = false) // Обязательно должно быть не null
    private Timetable timetable;

}
