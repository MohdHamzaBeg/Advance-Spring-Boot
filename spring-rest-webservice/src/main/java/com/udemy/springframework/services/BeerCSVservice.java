package com.udemy.springframework.services;

import java.io.File;
import java.util.List;

import com.udemy.springframework.models.BeerCSV;

public interface BeerCSVservice {
	List<BeerCSV> convertCSV(File csvFile);
}
