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
	
	private String name;

	private String location;
	
	private long inaugration;

	private float area;
	
	private String description;
	
	public Zoo() {
	}

	public Zoo(Integer userId, String name, String location, float area,
			String description) {
		this.name = name;
		this.location = location;
		this.inaugration = new Date().getTime();
		this.area = area;
		this.description = description;
		this.createdBy = userId;
		this.updatedBy = userId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public long getInaugration() {
		return inaugration;
	}

	public void setInaugration(long inaugration) {
		this.inaugration = inaugration;
	}

	public float getArea() {
		return area;
	}

	public void setArea(float area) {
		this.area = area;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
