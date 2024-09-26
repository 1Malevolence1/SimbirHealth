package com.example.Account_microservice.user.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "role")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {


    @Id
    @Column(name = "role_id")
    private Long id;

    @Column(name = "role_name", unique = true)
    private String roleName;
}
