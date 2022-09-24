package com.stackroute.authenticationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;



@SpringBootApplication
<<<<<<< HEAD


=======
@EnableEurekaClient
>>>>>>> d341023c222a42af237aa1ac84ad6992a8b14b3f
public class AuthenticationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationServiceApplication.class, args);
	}

}
