package com.udemy.springframework.entities;

import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Publisher {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private long zip;
	private String publisherName;
	private String address;
	private String city;
	private String state;
	
	@OneToMany(mappedBy = "publisher")
	public Set<book> books;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getZip() {
		return zip;
	}
	public void setZip(long zip) {
		this.zip = zip;
	}
	public String getPublisherName() {
		return publisherName;
	}
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "Publisher [id=" + id + ", zip=" + zip + ", publisherName=" + publisherName + ", address=" + address
				+ ", city=" + city + ", state=" + state + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(address, city, id, publisherName, state, zip);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Publisher other = (Publisher) obj;
		return Objects.equals(address, other.address) && Objects.equals(city, other.city) && id == other.id
				&& Objects.equals(publisherName, other.publisherName) && Objects.equals(state, other.state)
				&& zip == other.zip;
	}
	
	
}
