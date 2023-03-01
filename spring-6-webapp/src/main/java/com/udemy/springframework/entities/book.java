package com.udemy.springframework.entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class book {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String title;
	private String isbn;
	
	@ManyToMany
	   @JoinTable(name = "author_book", joinColumns = @JoinColumn(name= "book_id"),
	         inverseJoinColumns = @JoinColumn(name = "author_id"))
	   private Set<Author> authors = new HashSet<>();
	
	@ManyToMany
	@JoinTable(name = "author_book", joinColumns = @JoinColumn(name= "book_id"),
			inverseJoinColumns = @JoinColumn(name = "author_id"))

	public Set<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		book other = (book) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "book [id=" + id + ", title=" + title + ", isbn=" + isbn + ", authors=" + authors + "]";
	}
	
	
}
