package com.udemy.springframework.controllerss;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.springframework.entities.Customer;
import com.udemy.springframework.services.CustomerService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public List<Customer> listofCustomers(){
		return customerService.listofCustomers();
	}
	
	@GetMapping("/{customerid}")
	public Customer getCustomerById(@PathVariable("customerid") UUID id) {
		return customerService.getCustomerbyID(id);
	}
}
