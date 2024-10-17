package com.example.Document_microservice.document.service;

public interface CheckerDataAccount {


    void checkPacient(String token, Long accountId);
    void checkDoctor(String token, Long accountId);
    boolean verificationUser(Long pacientIdOfHistory, Long userId);

}
