package com.udemy.springframework.entities;

import java.time.LocalDateTime;
import java.util.Set;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
	@JdbcTypeCode(SqlTypes.INTEGER)
	private int id;
	
	@Column(length = 255)
	private String email; // We added this column knowingly to let hibernate fail the validation of the prev version of database
						  // Now I have to add a new version of database migration since I made changes to database schema 
	private String customerName;
	private float version;
	private LocalDateTime createdDate;
	private LocalDateTime lastModifiedDate;
	
	@OneToMany(mappedBy = "customer")
	private Set<BeerOrder> beerOrders;
}
