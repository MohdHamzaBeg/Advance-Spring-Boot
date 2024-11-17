package com.udemy.springframework.spring_reactive_mongo.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.udemy.springframework.spring_reactive_mongo.entity.Customer;

public interface CustomerRepository extends ReactiveMongoRepository<Customer, String> {

}
