package com.example.Hospital_microservice.security.restclient;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class RestClientForSecurityConfiguration {


    private final DiscoveryClient discoveryClient;

    @Bean
    public RestClient restClientSecurity() {
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("Account-microservice");
        if (serviceInstances.isEmpty()) {
            throw new IllegalStateException("No instances of Account-microservice found");
        }
        return RestClient.create(serviceInstances.get(0).getUri().toString());
    }
}
