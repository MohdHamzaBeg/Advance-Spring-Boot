package com.udemy.springframework.springrestwebservice.controllerstest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.udemy.springframework.controllerss.CustomerController;
import com.udemy.springframework.controllerss.NotFoundException;
import com.udemy.springframework.models.BeerModel;
import com.udemy.springframework.models.CustomerModel;
import com.udemy.springframework.services.CustomerService;
import com.udemy.springframework.services.CustomerServiceImpl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;
import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;


@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	CustomerService customerService;
	
	@Autowired
	ObjectMapper mapper;
	
	CustomerServiceImpl customerServiceImpl;
	
	@BeforeEach
	void setup() {
		customerServiceImpl = new CustomerServiceImpl();
	}
	
	@Test
	public void getcustomerbyidnotfound() throws Exception {
		given(customerService.getCustomerbyID(any(Integer.class))).willReturn(Optional.empty());
		
		mockMvc.perform(get("/customers/"+UUID.randomUUID()))
		.andExpect(status().isNotFound());
	}
	
	@Test
	void getCustomerbyId() throws Exception {
		CustomerModel testcustomer = customerServiceImpl.listofCustomers().get(0);
		given(customerService.getCustomerbyID(any(Integer.class))).willReturn(Optional.of(testcustomer));
		
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
	
	@Test
	void createcustomer() throws Exception {
		
		CustomerModel testcustomer= customerServiceImpl.listofCustomers().get(0);
		testcustomer.setId(null);
		
		given(customerService.saveCustomer(any(CustomerModel.class))).willReturn(customerServiceImpl.listofCustomers().get(1));
		
		mockMvc.perform(post("/customers/saveCustomer")
				.accept(MediaType.APPLICATION_JSON)
					.contentType(MediaType.APPLICATION_JSON)
					.content(mapper.writeValueAsString(testcustomer)));
	}
	
	@Test
	void updatecustomer() throws Exception {
		CustomerModel testcustomer = customerServiceImpl.listofCustomers().get(1);
		
		mockMvc.perform(put("/customers/update/"+UUID.randomUUID())
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(testcustomer)))
		.andExpect(status().isNoContent());
		
		verify(customerServiceImpl).updateCustomer(testcustomer);
	}
}
