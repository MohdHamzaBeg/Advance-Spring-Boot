package com.udemy.springframework.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.udemy.springframework.mappers.CustomerMapper;
import com.udemy.springframework.models.CustomerModel;
import com.udemy.springframework.repositories.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@Primary
@RequiredArgsConstructor
public class CustomerServiceJPA implements CustomerService {
	private CustomerRepository customerRepository;
	private CustomerMapper customerMapper;
	@Override
	public List<CustomerModel> listofCustomers() {
		return null;
	}

	@Override
	public Optional<CustomerModel> getCustomerbyID(UUID id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public CustomerModel saveCustomer(CustomerModel customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerModel updateCustomer(CustomerModel customer) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
