package com.example.web.entities;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Animal extends Audit {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "zoo_id", referencedColumnName = "id")
	private Zoo zoo;
	
	private String name;
	
	private String gender;
	
	private Date dob;
	
	private String species;
	
	private String habitat;

	public Animal(Zoo zoo, String name, String gender, String species, String habitat, Integer userId) {
		this.zoo = zoo;
		this.name = name;
		this.gender = gender;
		this.dob = new Date();
		this.species = species;
		this.habitat = habitat;
		this.createdOn = new Date();
		this.updatedOn = new Date();
		this.createdBy = userId;
		this.updatedBy = userId;
	}

	public Animal() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Zoo getZoo() {
		return zoo;
	}

	public void setZoo(Zoo zoo) {
		this.zoo = zoo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public String getHabitat() {
		return habitat;
	}

	public void setHabitat(String habitat) {
		this.habitat = habitat;
	}
}
