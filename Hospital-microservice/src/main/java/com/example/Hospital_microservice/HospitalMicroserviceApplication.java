package com.example.Hospital_microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class HospitalMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalMicroserviceApplication.class, args);
	}

}
