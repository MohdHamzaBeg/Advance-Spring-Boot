package com.udemy.springframework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.udemy.springframework.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
