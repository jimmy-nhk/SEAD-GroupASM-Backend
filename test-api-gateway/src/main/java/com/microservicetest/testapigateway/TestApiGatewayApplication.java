package com.microservicetest.testapigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TestApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestApiGatewayApplication.class, args);
	}

}
