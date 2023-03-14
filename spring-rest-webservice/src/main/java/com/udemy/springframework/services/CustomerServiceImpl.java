package com.udemy.springframework.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.udemy.springframework.models.CustomerModel;

//@Service
public class CustomerServiceImpl implements CustomerService {
	
	private Map<UUID, CustomerModel> customermap;
	
	

	public CustomerServiceImpl() {
		this.customermap = new HashMap<UUID, CustomerModel>();
		
		CustomerModel customer1 = CustomerModel.builder().
							id(UUID.randomUUID()).
							customerName("Bevda").
							version(11.1f).
							createdDate(LocalDateTime.now()).
							lastModifiedDate(LocalDateTime.now())
							.build();
		
		CustomerModel customer2 = CustomerModel.builder().
				id(UUID.randomUUID()).
				customerName("Nashedi").
				version(31.1f).
				createdDate(LocalDateTime.now()).
				lastModifiedDate(LocalDateTime.now())
				.build();
		
		CustomerModel customer3 = CustomerModel.builder().
				id(UUID.randomUUID()).
				customerName("Bhand").
				version(81.1f).
				createdDate(LocalDateTime.now()).
				lastModifiedDate(LocalDateTime.now())
				.build();
		
		customermap.put(customer1.getId(), customer1);
		customermap.put(customer2.getId(), customer2);
		customermap.put(customer3.getId(), customer3);
	}

	@Override
	public List<CustomerModel> listofCustomers() {
		return new ArrayList<CustomerModel>(customermap.values());
	}

	@Override
	public Optional<CustomerModel> getCustomerbyID(UUID id) {
		return Optional.of(customermap.get(id));
	}

	@Override
	public CustomerModel saveCustomer(CustomerModel customer) {
		CustomerModel savedCustomer = CustomerModel.builder()
				.id(UUID.randomUUID())
				.customerName(customer.getCustomerName())
				.createdDate(LocalDateTime.now())
				.lastModifiedDate(LocalDateTime.now())
				.version(customer.getVersion())
				.build();
		
		customermap.put(savedCustomer.getId(), savedCustomer);
		return savedCustomer;
	}

	@Override
	public CustomerModel updateCustomer(CustomerModel customer) {
		customer.setCreatedDate(LocalDateTime.now());
		customer.setLastModifiedDate(LocalDateTime.now());
		
		customermap.put(customer.getId(), customer);
		return null;
	}
	
}
