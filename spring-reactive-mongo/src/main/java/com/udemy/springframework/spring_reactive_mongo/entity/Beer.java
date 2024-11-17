package com.udemy.springframework.spring_reactive_mongo.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class Beer {
	
	@Id
	private String id;
	
	@NotBlank
	@NotNull
	private String beerName;
	
	private BigDecimal price;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;
	
}