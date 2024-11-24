package com.udemy.springframework.spring_reactive_mongo.webfn;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import lombok.RequiredArgsConstructor;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
@RequiredArgsConstructor
public class BeerRouterConfig {
	public static final String BEER_PATH = "/v3/beer";
	public static final String BEER_PATH_ID = "/v3/beer/{id}";
	
	private final BeerHandler handler;
	
	@Bean
	public RouterFunction<ServerResponse> beerRoutes(){
		return route()
				.GET(BEER_PATH, accept(APPLICATION_JSON), handler::listBeers)
				.GET(BEER_PATH_ID, accept(APPLICATION_JSON), handler::getBeerbyId)
				.POST(BEER_PATH+"/save", accept(APPLICATION_JSON), handler::saveBeer)
				.PUT(BEER_PATH_ID+"/update", accept(APPLICATION_JSON), handler::updateBeer)
				.DELETE(BEER_PATH_ID+"/delete", accept(APPLICATION_JSON), handler::deleteBeer)
				.build();
	}
}
