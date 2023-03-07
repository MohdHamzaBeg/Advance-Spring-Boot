package com.udemy.springframework.springrestwebservice.controllerstest;

import java.util.UUID;

import static org.hamcrest.core.Is.is;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.ContentResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import com.jayway.jsonpath.JsonPath;
import com.udemy.springframework.controllerss.BeerController;
import com.udemy.springframework.entities.Beer;
import com.udemy.springframework.services.BeerService;
import com.udemy.springframework.services.BeerServiceImpl;

@WebMvcTest(BeerController.class)
public class BeerControllerTest {
	@Autowired
	MockMvc mockMvc;
	
	@MockBean // This tells the spring contest that the controller is dependent on this bean, so it provides a Mockito Mock to mockly
			  // use service in our test
	BeerService beerService;
	
	BeerServiceImpl beerServiceImpl = new BeerServiceImpl();
	
	@Test
	void getbeerbyid() throws Exception {
		Beer testbeer = beerServiceImpl.listofBeers().get(0); 
		given(beerService.getBeerbyId(any(UUID.class))).willReturn(testbeer);
		// Below, we are creating a mock request for getBeerbyID method with the acceptable request type JSON and expected return type status
		mockMvc.perform(get("/beers/"+testbeer.getId())
				.accept(MediaType.APPLICATION_JSON))
		// The content method used below will check the test and map the returned value which it will find. Here, it is mapping the 
		// given() method which returning a Beer object
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)); // We are not gonna found any Beer with that ID as we are generating a random UUID at the runtime
	}
	
	@Test
	void listofbeers() throws Exception {
		given(beerService.listofBeers()).willReturn(beerServiceImpl.listofBeers());
		
		mockMvc.perform(get("/beers/list")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}
}