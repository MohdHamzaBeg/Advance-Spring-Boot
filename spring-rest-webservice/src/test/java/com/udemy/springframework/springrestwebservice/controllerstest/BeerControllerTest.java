package com.udemy.springframework.springrestwebservice.controllerstest;

import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.core.Is.is;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.ContentResultMatchers;
import org.springframework.web.client.HttpClientErrorException.NotFound;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.udemy.springframework.config.SpringSecurityConfig;
import com.udemy.springframework.controllerss.BeerController;
import com.udemy.springframework.controllerss.NotFoundException;
import com.udemy.springframework.models.BeerModel;
import com.udemy.springframework.services.BeerService;
import com.udemy.springframework.services.BeerServiceImpl;

@WebMvcTest(BeerController.class)
@Import(SpringSecurityConfig.class)
public class BeerControllerTest {
	@Autowired
	MockMvc mockMvc;
	
	@MockBean // This tells the spring contest that the controller is dependent on this bean, so it provides a Mockito Mock to mockly
			  // use service in our test
	BeerService beerService;
	
	@Autowired
	ObjectMapper mapper;
	
	BeerServiceImpl beerServiceImpl;
	
	@BeforeEach
	void setup() {
		beerServiceImpl =  new BeerServiceImpl(); 
	}
	
	@Test
	public void getbeerbyidnotfound() throws Exception {
		given(beerService.getBeerbyId(any(Integer.class))).willReturn(Optional.empty());
		
		mockMvc.perform(get("/beers/"+UUID.randomUUID()))
		.with(httpBasic(user1, password))
		.andExpect(status().isNotFound());
	}
	
	@Test
	void getbeerbyid() throws Exception {
		BeerModel testbeer = beerServiceImpl.listofBeers(null, null, null, null).get(0); 
		given(beerService.getBeerbyId(any(Integer.class))).willReturn(Optional.of(testbeer));
		// Below, we are creating a mock request for getBeerbyID method with the acceptable request type JSON and expected return type status
		mockMvc.perform(get("/beers/"+testbeer.getId())
				.with(httpBasic(user1, password))
				.accept(MediaType.APPLICATION_JSON))
		// The content method used below will check the test and map the returned value which it will find. Here, it is mapping the 
		// given() method which returning a BeerModel object
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)); // We are not gonna found any BeerModel with that ID as we are generating a random UUID at the runtime
	}
	
	@Test
	void listofbeers() throws Exception {
		given(beerService.listofBeers(null, null, null, null)).willReturn(beerServiceImpl.listofBeers(null, null, null, null));
		
		mockMvc.perform(get("/beers/list")
				.with(httpBasic(user1, password))
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}
	
	@Test
	void createbeer() throws Exception {
		
		BeerModel testbeer = beerServiceImpl.listofBeers(null, null, null, null).get(0);
		testbeer.setId(null);
		
		given(beerService.addBeer(any(BeerModel.class))).willReturn(beerServiceImpl.listofBeers(null, null, null, null).get(1));
		
		mockMvc.perform(post("/beers/saveBeer")
				.with(httpBasic(user1, password))
				.accept(MediaType.APPLICATION_JSON)
					.contentType(MediaType.APPLICATION_JSON)
					.content(mapper.writeValueAsString(testbeer)))
				.andExpect(header().exists("Location"));
	}
	
	@Test
	void updatebeer() throws Exception {
		BeerModel testbeer = beerServiceImpl.listofBeers(null, null, null, null).get(1);
		
		mockMvc.perform(put("/beers/update/"+UUID.randomUUID())
				.with(httpBasic(user1, password))
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(testbeer)))
		.andExpect(status().isNoContent());
		
		verify(beerService).updateBeer(any(BeerModel.class));
	}
	
	
}