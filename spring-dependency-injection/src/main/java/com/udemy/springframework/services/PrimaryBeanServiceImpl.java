package com.udemy.springframework.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
public class PrimaryBeanServiceImpl implements GreetingService{

	@Override
	public String greet() {
		return "Hello! I am the primary bean";
	}

}
