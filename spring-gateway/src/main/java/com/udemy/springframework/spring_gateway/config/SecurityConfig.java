package com.udemy.springframework.spring_gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) {
		http.authorizeExchange(ae-> ae.pathMatchers("/oauth2/**", "/oauth2/token").permitAll()
				.anyExchange().authenticated())
			.oauth2ResourceServer(o2rs-> o2rs.jwt(Customizer.withDefaults()))
			.csrf(ServerHttpSecurity.CsrfSpec::disable);
		return http.build();
	}
}
