package com.example.Timetable_microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TimetableMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimetableMicroserviceApplication.class, args);
	}

}
