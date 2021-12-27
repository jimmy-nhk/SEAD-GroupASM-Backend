package com.serviceregistrytest.testregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class TestRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestRegistryApplication.class, args);
	}

}
