package com.example.Timetable_microservice.timetable.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfiguration {


    @Bean
    public RestClient restClientForHospital(){
        return RestClient.create("http://localhost:8082");
    }

    @Bean
    public RestClient restClientForUser(){
        return RestClient.create("http://localhost:8081");
    }
}
