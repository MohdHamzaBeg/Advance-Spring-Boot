package com.udemy.springframework.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import org.springframework.stereotype.Service;

import com.opencsv.bean.CsvToBeanBuilder;
import com.udemy.springframework.models.BeerCSV;

@Service
public class BeerCSVserviceImpl implements BeerCSVservice{

	@Override
	public List<BeerCSV> convertCSV(File csvFile) {// Basically converting CSV data into POJOs
		try {
			List<BeerCSV> beercsvrecords = new CsvToBeanBuilder<BeerCSV>(new FileReader(csvFile))
					.withType(BeerCSV.class)
					.build().parse();
			return beercsvrecords;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
