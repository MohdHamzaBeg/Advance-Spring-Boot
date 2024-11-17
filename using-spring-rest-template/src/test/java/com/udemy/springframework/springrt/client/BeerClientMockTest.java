package com.udemy.springframework.springrt.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.web.client.MockServerRestTemplateCustomizer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.Http2;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.springframework.data.domain.Page;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Arrays;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.udemy.springframework.springrt.config.RestTemplateBuilderConfig;
import com.udemy.springframework.springrt.model.BeerModel;
import com.udemy.springframework.springrt.model.RestPageImpl;

// Actually this class is gonna test the client that the mock interaction sent to the server from the tests are working as 
// expected or not. Hence, a mock invokation of every http method is being sent in order to know the behaviour of the restclient.

@RestClientTest
@Import(RestTemplateBuilderConfig.class)
public class BeerClientMockTest {
	static final String URL = "http://localhost:8080";
	
	BeerClient beerClient;
	
	MockRestServiceServer server;
	
	@Autowired
	RestTemplateBuilder builder;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Mock
	RestTemplateBuilder mockBuilder = new RestTemplateBuilder(new MockServerRestTemplateCustomizer());
	// This line creates a new instance of the RestTemplateBuilder class using a custom RestTemplateCustomizer that configures it 
	// to use a mock server. 
	
	BeerModel beerModel;
	String jsonbeerModel;

	BeerModel getBeerDto(){
        return BeerModel.builder()
                .id(Long.valueOf(2477))
                .price(new BigDecimal("10.99"))
                .beerName("Mango Bobs")
                .build();
    }
	
	@BeforeEach
	void setUp() throws JsonProcessingException {
		RestTemplate restTemplate = builder.build();
		server = MockRestServiceServer.bindTo(restTemplate).build();
		// Creating a mockServer by binding the template and the Mock Rest service to test the http request.
		// The mockbuilder object will use this server to test the http request as a restClient.
		when(mockBuilder.build()).thenReturn(restTemplate);
		
		beerClient = new BeerClientImpl(mockBuilder);
		beerModel = getBeerDto();
		jsonbeerModel = objectMapper.writeValueAsString(beerModel);
	}

	@Test
	void testListBeers() throws JsonProcessingException  {
		String payload = objectMapper.writeValueAsString(getPage());
		
		server.expect(method(HttpMethod.GET))
		.andExpect(requestTo(URL+"/beers/list"))
		.andRespond(withSuccess(payload, MediaType.APPLICATION_JSON));
		
		 Page<BeerModel> dtos = beerClient.getBeerslist(null); // null is passed so that no query parameter can be passed.
		 // To test these methods with query parameters, seperate test can be written and a URIComponentsBuilder can be used
		 // to introduce the URL with query parameter extension in that test.
		 										
	        assertThat(dtos.getContent().size()).isGreaterThan(0);
	}
	
	@Test
	void testgetBeerbyId() {
		
		
		server.expect(method(HttpMethod.GET))
		.andExpect(requestToUriTemplate(URL+"/beers/{beerid}", beerModel.getId()))
		.andRespond(withSuccess(jsonbeerModel, MediaType.APPLICATION_JSON));
		
		BeerModel responsemodel = beerClient.getBeerbyId(beerModel.getId());
		assertThat(responsemodel.getId()).isEqualTo(beerModel.getId());
	}
	
	@Test
	void testCreateBeer() {
		
		URI uri = UriComponentsBuilder.fromPath("/beers/{beerid}").build(beerModel.getId());
		//this statement creates a URI that can be used to access a specific resource in a RESTful web service
		
		server.expect(method(HttpMethod.POST))
		.andExpect(requestTo(URL+"/beers/saveBeer"))
		.andRespond(withAccepted().location(uri));
		
		server.expect(method(HttpMethod.GET))
		.andExpect(requestToUriTemplate(URL+"/beers/{beerid}", beerModel.getId()))
		.andRespond(withSuccess(jsonbeerModel, MediaType.APPLICATION_JSON));
		
		BeerModel responsemodel = beerClient.createBeer(beerModel);
		assertThat(responsemodel.getId()).isEqualTo(beerModel.getId());
		
	}
	
	@Test
	void testupdateBeer() {
		server.expect(method(HttpMethod.PUT))
		.andExpect(requestToUriTemplate(URL+"/beers/update/{beerid}", beerModel.getId()))
		.andRespond(withNoContent());
		
		server.expect(method(HttpMethod.GET))
		.andExpect(requestToUriTemplate(URL+"/beers/{beerid}", beerModel.getId()))
		.andRespond(withSuccess(jsonbeerModel, MediaType.APPLICATION_JSON));
		
		BeerModel responsemodel = beerClient.updateBeer(beerModel, beerModel.getId());
		assertThat(responsemodel.getId()).isEqualTo(beerModel.getId());

	}
	
	RestPageImpl<BeerModel> getPage(){
        return new RestPageImpl(Arrays.asList(getBeerDto()), 1, 25, 1);
    }
	
}
