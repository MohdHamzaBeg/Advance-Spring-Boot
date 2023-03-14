package com.udemy.springframework.models;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CustomerModel {
	private Long id;
	private String customerName;
	private float version;
	private LocalDateTime createdDate;
	private LocalDateTime lastModifiedDate;

}
