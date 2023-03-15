package com.udemy.springframework.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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
	private final CustomerRepository customerRepository;
	private final CustomerMapper customerMapper;
	@Override
	public List<CustomerModel> listofCustomers() {
		return customerRepository.findAll()
				.stream()
				.map(customerMapper::customertocustomerModel)
				.collect(Collectors.toList());
	}

	@Override
	public Optional<CustomerModel> getCustomerbyID(long id) {
		return Optional.ofNullable(customerMapper.customertocustomerModel(customerRepository.findById(id).orElse(null)));
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
