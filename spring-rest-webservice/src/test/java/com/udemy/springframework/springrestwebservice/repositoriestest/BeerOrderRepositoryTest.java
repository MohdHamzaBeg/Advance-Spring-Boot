package com.udemy.springframework.springrestwebservice.repositoriestest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import com.udemy.springframework.entities.Beer;
import com.udemy.springframework.entities.BeerOrder;
import com.udemy.springframework.entities.Customer;
import com.udemy.springframework.repositories.BeerOrderRepository;
import com.udemy.springframework.repositories.BeerRepository;
import com.udemy.springframework.repositories.CustomerRepository;

import jakarta.transaction.Transactional;

// This class is majorily made to test the database relationships betweeen beer, customer, beerorder, beerorderline 

@SpringBootTest
public class BeerOrderRepositoryTest {
	
	@Autowired
	BeerOrderRepository beerOrderRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	BeerRepository beerRepository;
	
	Beer testbeer;
	Customer testCustomer;
	
	@BeforeEach
	void setup() {
		testbeer = beerRepository.findAll().get(0);
		testCustomer = customerRepository.findAll().get(0);
	}
	
	@Transactional
	@Test
	void testBeerOrders() {
		BeerOrder beerOrder = BeerOrder.builder()
				.id("1")
				.customerRef("Test customer")
				.customer(testCustomer)
				.build();
		BeerOrder savedBeerOrder = beerOrderRepository.saveAndFlush(beerOrder);
		System.out.println(savedBeerOrder.getCustomer().getCustomerName());
	}
}
