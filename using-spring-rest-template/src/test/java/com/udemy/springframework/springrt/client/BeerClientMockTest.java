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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.springframework.data.domain.Page;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.math.BigDecimal;
import java.util.Arrays;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.udemy.springframework.springrt.config.RestTemplateBuilderConfig;
import com.udemy.springframework.springrt.model.BeerModel;
import com.udemy.springframework.springrt.model.RestPageImpl;

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
	
	@BeforeEach
	void setUp() {
		RestTemplate restTemplate = builder.build();
		server = MockRestServiceServer.bindTo(restTemplate).build();
		// Creating a mockServer by binding the template and the Mock Rest service to test the http request.
		// The mockbuilder object will use this server to the http request as a restClient.
		when(mockBuilder.build()).thenReturn(restTemplate);
		beerClient = new BeerClientImpl(mockBuilder);
	}

	@Test
	void testListBeers() throws JsonProcessingException {
		String payload = objectMapper.writeValueAsString(getPage());
		
		server.expect(method(HttpMethod.GET))
		.andExpect(requestTo(URL+"/beers/list"))
		.andRespond(withSuccess(payload, MediaType.APPLICATION_JSON));
		
		 Page<BeerModel> dtos = beerClient.getBeerslist(null); // null is passed so that no query parameter can be passed.
		 										
	        assertThat(dtos.getContent().size()).isGreaterThan(0);
	}
	
	@Test
	void testgetBeerbyId() throws JsonProcessingException {
		BeerModel model = getBeerDto();
		String response = objectMapper.writeValueAsString(model);
		
		server.expect(method(HttpMethod.GET))
		.andExpect(requestToUriTemplate(URL+"/beers/{beerid}", model.getId()))
		.andRespond(withSuccess(response, MediaType.APPLICATION_JSON));
		
		BeerModel responsemodel = beerClient.getBeerbyId(model.getId());
		assertThat(responsemodel.getId()).isEqualTo(model.getId());
	}
	
	BeerModel getBeerDto(){
        return BeerModel.builder()
                .id(Long.valueOf(2477))
                .price(new BigDecimal("10.99"))
                .beerName("Mango Bobs")
                .build();
    }
	
	RestPageImpl<BeerModel> getPage(){
        return new RestPageImpl(Arrays.asList(getBeerDto()), 1, 25, 1);
    }
	
}
