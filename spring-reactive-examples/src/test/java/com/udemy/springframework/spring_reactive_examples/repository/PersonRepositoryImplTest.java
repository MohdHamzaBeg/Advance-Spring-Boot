package com.udemy.springframework.spring_reactive_examples.repository;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.udemy.springframework.spring_reactive_examples.domain.Person;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PersonRepositoryImplTest {
	 PersonRepository personRepository = new PersonRepositoryImpl();

	    @Test
	    void testMonoByIdBlock() {
	        Mono<Person> personMono = personRepository.getbyID(1);

	        Person person = personMono.block();

	        System.out.println(
	                person.toString()
	        );
	    }

	    @Test
	    void testGetByIdSubscriber() {
	        Mono<Person> personMono = personRepository.getbyID(1);

	        personMono.subscribe(person -> {
	            System.out.println(person.toString());
	        });
	    }

	    @Test
	    void testMapOperation() {
	        Mono<Person> personMono = personRepository.getbyID(1);

	        personMono.map(Person::getFirstName).subscribe(firstName -> {
	            System.out.println(firstName);
	        });
	    }

	    @Test
	    void testFluxBlockFirst() {
	        Flux<Person> personFlux = personRepository.getAll();

	        Person person = personFlux.blockFirst();

	        System.out.println(person.toString());
	    }

	    @Test
	    void testFluxSubscriber() {
	        Flux<Person> personFlux = personRepository.getAll();

	        personFlux.subscribe(person -> {
	            System.out.println(person.toString());
	        });
	    }

	    @Test
	    void testFluxMap() {
	        Flux<Person> personFlux = personRepository.getAll();

	        personFlux.map(Person::getFirstName).subscribe(fistName -> System.out.println(fistName));
	    }

	    @Test
	    void testFluxToList() {
	        Flux<Person> personFlux = personRepository.getAll();

	        Mono<List<Person>> listMono = personFlux.collectList();

	        listMono.subscribe(list -> {
	            list.forEach(person -> System.out.println(person.getFirstName()));
	        });
	     }

	    @Test
	    void testFilterOnName() {
	        personRepository.getAll()
	                .filter(person -> person.getFirstName().equals("Fiona"))
	                .subscribe(person -> System.out.println(person.getFirstName()));
	    }

	    @Test
	    void testGetById() {
	        Mono<Person> fionaMono = personRepository.getAll().filter(person -> person.getFirstName().equals("Fiona"))
	                .next();

	        fionaMono.subscribe(person -> System.out.println(person.getFirstName()));
	    }
	    
	    @Test
	    void getbyIdNotFound() {
	    	Flux<Person> personFlux = personRepository.getAll();
	    	
	    	final Integer id = 6;
	    	
	    	Mono<Person> personMono = personFlux.filter(person -> person.getId()==id).single()
	    			.doOnError(throwable -> {
	                    System.out.println("Error occurred in flux");
	                    System.out.println(throwable.toString());
	                });

	        personMono.subscribe(person -> {
	            System.out.println(person.toString());
	        }, throwable -> {
	            System.out.println("Error occurred in the mono");
	            System.out.println(throwable.toString());
	        });
	    	
	    	
	    }
	    
}




