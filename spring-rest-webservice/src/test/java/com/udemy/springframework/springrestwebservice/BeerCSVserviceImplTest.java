package com.udemy.springframework.springrestwebservice;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.util.ResourceUtils;

import com.udemy.springframework.models.BeerCSV;
import com.udemy.springframework.services.BeerCSVservice;
import com.udemy.springframework.services.BeerCSVserviceImpl;

public class BeerCSVserviceImplTest {
	BeerCSVservice beerCSVservice = new BeerCSVserviceImpl();
	
	@Test
	void convertCSV() throws FileNotFoundException {
		File file = ResourceUtils.getFile("classpath:csvdata/beers.csv");
		List<BeerCSV> recs = beerCSVservice.convertCSV(file);
		System.out.println(recs.size());
		assertThat(recs.size()).isGreaterThan(0);
	}
}
