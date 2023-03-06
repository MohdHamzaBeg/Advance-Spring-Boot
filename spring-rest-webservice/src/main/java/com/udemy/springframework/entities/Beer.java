package com.udemy.springframework.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Beer {
	private UUID id;
	private String beerName;
	private BigDecimal price;
	private LocalDateTime mfg;
	private LocalDateTime exp;
	
}
