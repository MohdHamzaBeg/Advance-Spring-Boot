package com.udemy.springframework.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.udemy.springframework.entities.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	private Map<UUID, Customer> customermap;
	
	

	public CustomerServiceImpl() {
		this.customermap = new HashMap<UUID, Customer>();
		
		Customer customer1 = Customer.builder().
							id(UUID.randomUUID()).
							customerName("Bevda").
							version(11.1f).
							createdDate(LocalDateTime.now()).
							lastModifiedDate(LocalDateTime.now())
							.build();
		
		Customer customer2 = Customer.builder().
				id(UUID.randomUUID()).
				customerName("Nashedi").
				version(31.1f).
				createdDate(LocalDateTime.now()).
				lastModifiedDate(LocalDateTime.now())
				.build();
		
		Customer customer3 = Customer.builder().
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
	public List<Customer> listofCustomers() {
		return new ArrayList<Customer>(customermap.values());
	}

	@Override
	public Customer getCustomerbyID(UUID id) {
		return customermap.get(id);
	}

	@Override
	public Customer saveCustomer(Customer customer) {
		Customer savedCustomer = Customer.builder()
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
	public Customer updateCustomer(Customer customer) {
		customer.setCreatedDate(LocalDateTime.now());
		customer.setLastModifiedDate(LocalDateTime.now());
		
		customermap.put(customer.getId(), customer);
		return null;
	}
	
}
