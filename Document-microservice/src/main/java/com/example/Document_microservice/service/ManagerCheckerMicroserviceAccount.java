package com.example.Document_microservice.service;

public interface ManagerCheckerMicroserviceAccount {

    boolean roleMatchingCheck(String token, Long accountId);
}
