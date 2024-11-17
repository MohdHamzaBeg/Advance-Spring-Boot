package com.udemy.springframework.spring_reactive.service;

import com.udemy.springframework.spring_reactive.entity.Beer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BeerService {
	
	Flux<Beer> listBeers();

	Mono<Beer> getbyId(Integer id);

	Mono<Beer> save(Beer beer);

	Mono<Beer> update(Integer id, Beer beer);

	Mono<Void> delete(Integer id);
}
