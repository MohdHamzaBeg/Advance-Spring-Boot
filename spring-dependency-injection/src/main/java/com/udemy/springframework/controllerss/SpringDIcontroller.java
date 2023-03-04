package com.udemy.springframework.controllerss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.udemy.springframework.services.GreetingService;
import com.udemy.springframework.services.GreetingServiceImpl;

@Controller
public class SpringDIcontroller {
	
	
	private final GreetingService greetingService;


	public SpringDIcontroller(@Qualifier("nonprimarybean") GreetingService greetingService) {
		this.greetingService = greetingService;
	}


	public String sayHello() {
		return greetingService.greet();
	}
}
