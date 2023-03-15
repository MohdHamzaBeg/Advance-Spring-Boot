package com.udemy.springframework.mappers;

import org.springframework.stereotype.Service;

import com.udemy.springframework.entities.Customer;
import com.udemy.springframework.models.CustomerModel;

@Service
public class CustomerMapperImpl implements CustomerMapper {

	@Override
	public Customer customerModeltocustomer(CustomerModel customerModel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerModel customertocustomerModel(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

}
