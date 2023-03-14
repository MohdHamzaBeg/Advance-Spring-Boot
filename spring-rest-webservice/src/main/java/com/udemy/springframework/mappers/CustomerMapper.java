package com.udemy.springframework.mappers;

import org.mapstruct.Mapper;

import com.udemy.springframework.entities.Customer;
import com.udemy.springframework.models.CustomerModel;

@Mapper
public interface CustomerMapper {
	Customer customerModeltocustomer(CustomerModel customerModel);
	CustomerModel cutstomertocustomerModel(Customer customer);
}
