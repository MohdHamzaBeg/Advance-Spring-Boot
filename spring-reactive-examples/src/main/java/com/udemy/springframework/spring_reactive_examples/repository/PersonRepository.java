package com.udemy.springframework.spring_reactive_examples.repository;

import com.udemy.springframework.spring_reactive_examples.domain.Person;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonRepository {
	
	Mono<Person> getbyID(Integer id);
	
	Flux<Person> getAll();
}
