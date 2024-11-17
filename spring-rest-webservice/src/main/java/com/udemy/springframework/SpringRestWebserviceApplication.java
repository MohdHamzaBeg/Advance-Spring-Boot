package com.udemy.springframework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringRestWebserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRestWebserviceApplication.class, args);
	}

}
// Note- Refactor the tests to allow them to accept the JWT tokens