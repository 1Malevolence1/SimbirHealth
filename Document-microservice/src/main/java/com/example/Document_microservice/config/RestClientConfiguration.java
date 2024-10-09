package com.example.Document_microservice.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfiguration {

    @Bean
    public RestClient restClientMicroserviceAccount(){
        return RestClient.create("http://localhost:8081");
    }

    @Bean
    public RestClient restClientMicroserviceHospital(){
        return RestClient.create("http://localhost:8082");
    }
}
