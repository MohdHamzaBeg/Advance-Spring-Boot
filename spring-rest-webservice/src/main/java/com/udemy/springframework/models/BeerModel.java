package com.udemy.springframework.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BeerModel {
	private Long id; // Change to int later so that the get request will be easy for getbeerbyID
	private String beerName;
	private BigDecimal price;
	private LocalDateTime mfg;
	private LocalDateTime exp;
	
}
