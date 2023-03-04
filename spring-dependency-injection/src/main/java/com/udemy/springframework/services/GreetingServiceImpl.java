package com.udemy.springframework.services;

import org.springframework.stereotype.Service;

@Service("nonprimarybean") /** if two implements of same service contains same qualifier name, then use @Profile annotation and then
						   		provide a new name to that profile to differentiate between the beans
						        Profile is brought into the spring context only if the profile is set active by the injected class using
						   		@ActiveProfile annotation.
						   		If no profile is set active, then the profile which is attributed as "default" will be automatically
						   		brought*/
public class GreetingServiceImpl implements GreetingService {

	@Override
	public String greet() {
		return "Hello Everyone from Service";
	}

}
