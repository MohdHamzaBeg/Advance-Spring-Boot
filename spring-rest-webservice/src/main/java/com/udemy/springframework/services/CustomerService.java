package com.udemy.springframework.services;

import java.util.List;
import java.util.UUID;

import com.udemy.springframework.entities.Customer;

public interface CustomerService {
	List<Customer> listofCustomers();
	Customer getCustomerbyID(UUID id);
}
