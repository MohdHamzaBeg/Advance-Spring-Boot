package com.udemy.springframework.controllerss;

import org.springframework.stereotype.Controller;

import com.udemy.springframework.services.GreetingService;
import com.udemy.springframework.services.GreetingServiceImpl;

//@Controller
public class MyController {
	
	private final GreetingService greetingService;
	
	
	public MyController() {
		this.greetingService = new GreetingServiceImpl();
	}


	public String sayHello() {
		return greetingService.greet();
	}
	
}
