package com.example.Document_microservice.security.restclient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientForSecurityConfiguration {

    @Bean
    public RestClient restClientSecurity(){
        return RestClient.create("http://localhost:8081");
    }
}
