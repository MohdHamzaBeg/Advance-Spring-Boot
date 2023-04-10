package com.udemy.springframework.entities;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// This entity has been created even after the creation of beer_order and beer_order_line so as to possess many to many relationship
// with the beer entity. That is why we have made an extra join table in order to join the mapped properties of beer and category
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Category {
	
	@Id
	@JdbcTypeCode(SqlTypes.VARCHAR)
	@Column(length = 36, columnDefinition = "varchar(36)")
	private String id;
	
	@Version
	private Long version;
	
	@CreationTimestamp
	@Column(updatable = false)
	private Timestamp createdDate;
	
	@UpdateTimestamp
	@Column(updatable = true)
	private Timestamp lastModifiedDate;
	
	private String description;
	
	@Builder.Default
	@ManyToMany
	@JoinTable(name = "beer_category",
			joinColumns = @JoinColumn(name="category_id"),
			inverseJoinColumns = @JoinColumn(name="beer_id"))
	private Set<Beer> beers = new HashSet<>();
}
