package com.udemy.springframework.spring_reactive.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.udemy.springframework.spring_reactive.entity.Beer;

public interface BeerRepository extends ReactiveCrudRepository<Beer, Integer>{

}
