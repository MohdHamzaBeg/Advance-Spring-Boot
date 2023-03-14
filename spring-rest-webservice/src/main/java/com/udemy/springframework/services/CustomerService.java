package com.udemy.springframework.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.udemy.springframework.models.CustomerModel;

public interface CustomerService {
	List<CustomerModel> listofCustomers();
	Optional<CustomerModel> getCustomerbyID(UUID id);
	CustomerModel saveCustomer(CustomerModel customer);
	CustomerModel updateCustomer(CustomerModel customer);
}
