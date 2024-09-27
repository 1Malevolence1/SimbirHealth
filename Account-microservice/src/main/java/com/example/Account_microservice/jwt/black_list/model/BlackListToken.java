package com.example.Account_microservice.jwt.black_list.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "black_listed_token")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlackListToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "black_listed_token_id")
    private Long id;

    @Column(name = "token", unique = true)
    private String token;

    @Column(name = "expiration_time")
    private Long expirationTime;

}
