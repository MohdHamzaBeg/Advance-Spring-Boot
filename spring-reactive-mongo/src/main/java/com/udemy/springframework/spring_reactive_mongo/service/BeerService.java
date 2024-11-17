package com.udemy.springframework.spring_reactive_mongo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.udemy.springframework.spring_reactive_mongo.entity.Beer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface BeerService {
	
	Mono<Beer> save(Mono<Beer> beer);
	
	Mono<Beer> getbyId(String beerId);
	
	Flux<Beer> findbybeername(String beerName);

	Flux<Beer> listofbeers();
	
	Mono<Beer> update(String beerId, Beer beer);

	Mono<Void> delete(String beerId);
	
	
}
