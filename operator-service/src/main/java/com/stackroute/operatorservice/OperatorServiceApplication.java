package com.stackroute.operatorservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class OperatorServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OperatorServiceApplication.class, args);
	}

}
