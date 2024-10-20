package com.example.Document_microservice.document.config;


import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class RestClientConfiguration {

    private final DiscoveryClient discoveryClient;

    @Bean
    public RestClient restClientForHospital(){
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("Hospital-microservice");
        if (serviceInstances.isEmpty()){
            throw new IllegalStateException("No instances of Hospital-microservice found");
        }
        return RestClient.create(serviceInstances.get(0).getUri().toString());
    }

    @Bean
    public RestClient restClientForUser(){
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("Account-microservice");
        if (serviceInstances.isEmpty()){
            throw new IllegalStateException("No instances of Account-microservice found");
        }
        return RestClient.create(serviceInstances.get(0).getUri().toString());
    }
}
