package com.udemy.springframework.springrt.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.client.RestTemplateBuilderConfigurer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.AuthorizedClientServiceOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.web.util.DefaultUriBuilderFactory;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Configuration
public class RestTemplateBuilderConfig {
	
	private final ClientRegistrationRepository clientRegistrationRepository;
	private final OAuth2AuthorizedClientService auth2AuthorizedClientService;

	OAuth2AuthorizedClientManager auth2AuthorizedClientManager() {
		var authorisedClientProvider = OAuth2AuthorizedClientProviderBuilder.builder()
				.clientCredentials().build();
		
		var authorisedClientManager = new AuthorizedClientServiceOAuth2AuthorizedClientManager
				(clientRegistrationRepository, auth2AuthorizedClientService);
		authorisedClientManager.setAuthorizedClientProvider(authorisedClientProvider);
		return authorisedClientManager;
	}
	
	@Bean
	RestTemplateBuilder restTemplateBuilder(RestTemplateBuilderConfigurer configurer) {
		
		return configurer.configure(new RestTemplateBuilder()) // getting builder object
				.uriTemplateHandler(new DefaultUriBuilderFactory("http://localhost:8080")); // getting uri builder using templatehandler
	}
	
}