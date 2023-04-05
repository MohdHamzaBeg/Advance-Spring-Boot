package com.udemy.springframework.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BeerModel {
	private Long id; // Change to int later so that the get request will be easy for getbeerbyID
	
	@NotBlank
	@NotNull
	@Size(max = 50)
	private String beerName;
	//@NotBlank
	@NotNull
	private BigDecimal price;
	private LocalDateTime mfg;
	private LocalDateTime exp;
	
}
