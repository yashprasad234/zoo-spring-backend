package com.example.web.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Zoo extends Audit{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String city;
	
	private String state;
	
	private String country;
	
	private int capacity;
	
	private int species;
	
	private int endageredSpecies;
	
	private long inaugration;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getSpecies() {
		return species;
	}

	public void setSpecies(int species) {
		this.species = species;
	}

	public int getEndageredSpecies() {
		return endageredSpecies;
	}

	public void setEndageredSpecies(int endageredSpecies) {
		this.endageredSpecies = endageredSpecies;
	}

	public long getInaugration() {
		return inaugration;
	}

	public void setInaugration(long inaugration) {
		this.inaugration = inaugration;
	}

	public Zoo(String city, String state, String country, int capacity, int species,
			int endageredSpecies, long inaugration, Integer userId) {
		this.city = city;
		this.state = state;
		this.country = country;
		this.capacity = capacity;
		this.inaugration = inaugration;
		this.createdOn = new Date();
		this.updatedOn = new Date();
		this.createdBy = userId;
		this.updatedBy = userId;
	}

	public Zoo() {
	}
	
}
