package com.udemy.springframework.spring_reactive_mongo.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.udemy.springframework.spring_reactive_mongo.entity.Beer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BeerRepository extends ReactiveMongoRepository<Beer, String> {

	Flux<Beer> findAllByBeerName(String beerName);
	
}
