package com.example.Document_microservice.service;

public interface CheckerDataAccount {


    void checkPacient(String token, Long accountId);
    void checkDoctor(String toke, Long accountId);

}
