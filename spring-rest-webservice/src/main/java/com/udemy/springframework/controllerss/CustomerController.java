package com.udemy.springframework.controllerss;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.springframework.models.BeerModel;
import com.udemy.springframework.models.CustomerModel;
import com.udemy.springframework.services.CustomerService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public List<CustomerModel> listofCustomers(){
		return customerService.listofCustomers();
	}
	
	@GetMapping("/{customerid}")
	public CustomerModel getCustomerById(@PathVariable("customerid") UUID id) {
		return customerService.getCustomerbyID(id).orElseThrow(NotFoundException::new);
	}
	
	@PostMapping("/saveCustomer")
	public ResponseEntity saveCustomer(@RequestBody CustomerModel customer) {
		
		CustomerModel savedCustomer = customerService.saveCustomer(customer);
		HttpHeaders headers = new HttpHeaders();
		headers.add("CustomerName and ID", savedCustomer.getCustomerName()+" "+savedCustomer.getId());
		
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{customerid}")
	public ResponseEntity updateBeer(@PathVariable("customerid") UUID id, @RequestBody CustomerModel customer) {
		
		CustomerModel existingcustomer = customerService.getCustomerbyID(id).orElseThrow(NotFoundException::new);
		customer.setId(existingcustomer.getId());
		CustomerModel updatedcustomer = customerService.updateCustomer(customer);
		
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
}
