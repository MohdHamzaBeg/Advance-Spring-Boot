package com.udemy.springframework.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class Beer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull // These annotations are placed here so that no violation occurs at the time of retrieving from database
	@NotBlank
	@Size(max = 50)
	//@Column(length = 50)// Throws dataintegrityviolation error, which is not specific
	private String beerName;
	
	@NotNull
	private BigDecimal price;
	private LocalDateTime mfg;
	
	@OneToMany(mappedBy = "beer")
	private Set<BeerOrderLine> beerOrderLines;
	
	@Builder.Default
	@ManyToMany
	@JoinTable(name="beer_category",
			joinColumns = @JoinColumn(name="beer_id"),
			inverseJoinColumns = @JoinColumn(name="category_id"))
	private Set<Category> categories = new HashSet<>();
	
	public void addCategory(Category category) {
		this.categories.add(category); // Adding a category from the test method to this beer
		category.getBeers().add(this); // Adding this beer to the same category mentioned above
	}
	
}
