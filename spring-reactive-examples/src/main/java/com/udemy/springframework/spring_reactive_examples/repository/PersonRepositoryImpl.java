package com.udemy.springframework.spring_reactive_examples.repository;

import com.udemy.springframework.spring_reactive_examples.domain.Person;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PersonRepositoryImpl implements PersonRepository {
// Since we are not using Spring JPA so creating the implementation of the repository
// to illustrate the use of Mono and Flux in spring for the persistance operation
	Person p1 = Person.builder()
			.id(1)
			.firstName("Wade")
			.lastName("Wilson")
			.build();
	
	Person p2 = Person.builder()
			.id(2)
			.firstName("James")
			.lastName("Howlett")
			.build();
	
	Person p3 = Person.builder()
			.id(3)
			.firstName("Charles")
			.lastName("Xavier")
			.build();
	
	Person p4 = Person.builder()
			.id(4)
			.firstName("Eric")
			.lastName("Lensherr")
			.build();
	
	@Override
	public Mono<Person> getbyID(Integer id) {
		return Mono.just(p1); 
	}

	@Override
	public Flux<Person> getAll() {
		return Flux.just(p1, p2, p3, p4);
	}

}
