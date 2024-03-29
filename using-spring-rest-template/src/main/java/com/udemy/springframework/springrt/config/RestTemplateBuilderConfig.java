package com.udemy.springframework.springrt.config;

import org.springframework.boot.autoconfigure.web.client.RestTemplateBuilderConfigurer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class RestTemplateBuilderConfig {
	@Bean
	RestTemplateBuilder restTemplateBuilder(RestTemplateBuilderConfigurer configurer) {
		RestTemplateBuilder builder = configurer.configure(new RestTemplateBuilder());
		DefaultUriBuilderFactory builderFactory = new
				DefaultUriBuilderFactory("http://localhost:8080");
		
		return builder.uriTemplateHandler(builderFactory);
	}
	
}
