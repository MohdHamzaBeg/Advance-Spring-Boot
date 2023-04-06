package com.udemy.springframework.springrestwebservice.controllerstest;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import com.udemy.springframework.controllerss.BeerController;
import com.udemy.springframework.entities.Beer;
import com.udemy.springframework.mappers.BeerMapper;
import com.udemy.springframework.models.BeerModel;
import com.udemy.springframework.repositories.BeerRepository;

@SpringBootTest
public class BeerControllerIntegrationTest {
	@Autowired
	BeerController beerController;
	
	@Autowired
	BeerRepository beerRepository;
	
	@Autowired
	BeerMapper beerMapper;
	
	@Test
	void testlistofbeers() {
		List<BeerModel> beermodels = beerController.getBeersList();
		
		assertThat(beermodels.size()).isEqualTo(2413);
	}
	
	@Test
	void getbeerbyid() {
		Beer beer = beerRepository.findAll().get(1);
		
		BeerModel beerModel = beerController.getBeerById(beer.getId());
		
		assertThat(beerModel).isNotNull();
	}
	
	@Test
	void savenewBeer() {
		BeerModel model = BeerModel.builder().beerName("Daaru").price(new BigDecimal(5656)).build();
		ResponseEntity<BeerModel> responseEntity = beerController.saveBeer(model);
		//assertThat(responseEntity).isNotNull();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.valueOf(201));
		//assertThat(responseEntity.getHeaders().getLocation()).isNotNull();
			
	}
	@Test
	void updateBeer() {
		Beer beer = beerRepository.findAll().get(0);
		BeerModel newmodel = beerMapper.beertoBeerModel(beer);
		newmodel.setId(null);
		final String beername = "UpdatedBeer";
		newmodel.setBeerName(beername);
		
		ResponseEntity responseEntity = beerController.updateBeer(beer.getId(), newmodel);
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));
		
		Beer updatedBeer = beerRepository.findById(beer.getId()).get();
		assertThat(updatedBeer.getBeerName()).isEqualTo(beername);
		
		
	}
}
