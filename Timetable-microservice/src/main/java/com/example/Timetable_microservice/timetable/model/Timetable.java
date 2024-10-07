package com.example.Timetable_microservice.timetable.model;




import com.example.Timetable_microservice.appointment.model.Appointment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "timetable")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Timetable  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "timetable_id")
    private Long id;

    @Column(name = "hospital_id")
    private Long hospitalId;

    @Column(name = "doctor_id")
    private Long doctorId;

    @Column(name = "start_time")
    private LocalDateTime from;

    @Column(name = "end_time")
    private LocalDateTime to;

    private String room;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "timetable_id")
    private List<Appointment> appointments;
}
