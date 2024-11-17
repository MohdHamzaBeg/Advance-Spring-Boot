package com.udemy.springframework.spring_reactive_mongo.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class Customer {
	
	@Id
	private String id;
	
	@NotBlank
	private String customerName;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;
}
