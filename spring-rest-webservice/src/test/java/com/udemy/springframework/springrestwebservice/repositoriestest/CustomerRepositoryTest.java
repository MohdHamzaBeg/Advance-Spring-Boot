package com.udemy.springframework.springrestwebservice.repositoriestest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.udemy.springframework.entities.Customer;
import com.udemy.springframework.repositories.CustomerRepository;

@DataJpaTest
public class CustomerRepositoryTest {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Test
	void testsaveCustomer() {
		Customer savedCustomer = customerRepository.save(Customer.builder().customerName("Tunn").build());
		
		assertThat(savedCustomer).isNotNull();
		assertThat(savedCustomer.getId()).isNotNull();
	}
	
}
