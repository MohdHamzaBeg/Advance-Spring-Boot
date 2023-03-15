package com.udemy.springframework.springrestwebservice.controllerstest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.udemy.springframework.controllerss.CustomerController;
import com.udemy.springframework.models.CustomerModel;
import com.udemy.springframework.repositories.CustomerRepository;

@SpringBootTest
public class CustomerControllerIntegrationTest {
	@Autowired
	CustomerController customerController;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Test
	void listofCustomer() {
		List<CustomerModel> models = customerController.listofCustomers();
		assertThat(models.size()).isEqualTo(3);
	}
	
	@Test
	void getcustomerbyId() {
		CustomerModel model = customerController.getCustomerById(1);
		assertThat(model).isNotNull();
	}
}
