package com.udemy.springframework.springrestwebservice.controllerstest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.udemy.springframework.controllerss.CustomerController;
import com.udemy.springframework.entities.Customer;
import com.udemy.springframework.services.CustomerService;
import com.udemy.springframework.services.CustomerServiceImpl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	CustomerService customerService;
	
	CustomerServiceImpl customerServiceImpl = new CustomerServiceImpl();
	
	@Test
	void getCustomerbyId() throws Exception {
		Customer testcustomer = customerServiceImpl.listofCustomers().get(0);
		given(customerService.getCustomerbyID(any(UUID.class))).willReturn(testcustomer);
		
		mockMvc.perform(get("/customers/"+testcustomer.getId())
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}
	@Test
	void listofcustomers() throws Exception{
		given(customerService.listofCustomers()).willReturn(customerServiceImpl.listofCustomers());
		mockMvc.perform(get("/customers/list")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}
}
