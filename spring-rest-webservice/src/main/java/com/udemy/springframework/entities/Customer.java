package com.udemy.springframework.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length = 255)
	private String email; // We added this column knowingly to let hibernate fail the validation of the prev version of database
						  // Now I have to add a new version of database migration since I made changes to database schema 
	private String customerName;
	private float version;
	private LocalDateTime createdDate;
	private LocalDateTime lastModifiedDate;
}
