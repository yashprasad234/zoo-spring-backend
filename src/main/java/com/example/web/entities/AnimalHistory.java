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
public class AnimalHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(referencedColumnName = "id")
	private Zoo from;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(referencedColumnName = "id")
	private Zoo to;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(referencedColumnName = "id")
	private Animal animal;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(referencedColumnName = "id")
	private User user;

	private Date transferredOn;


	public AnimalHistory() {
	}

	public AnimalHistory(Zoo from_zoo, Zoo to_zoo, Animal animal_id, User user) {
		this.user = user;
		this.animal = animal_id;
		this.from = from_zoo;
		this.to = to_zoo;
		this.transferredOn = new Date();
	}
	
	public Animal getAnimal_id() {
		return animal;
	}

	public void setAnimal_id(Animal animal_id) {
		this.animal = animal_id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Zoo getFrom_zoo() {
		return from;
	}

	public void setFrom_zoo(Zoo from_zoo) {
		this.from = from_zoo;
	}

	public Zoo getTo_zoo() {
		return to;
	}

	public void setTo_zoo(Zoo to_zoo) {
		this.to = to_zoo;
	}

	public Date getTransferredOn() {
		return transferredOn;
	}

	public void setTransferredOn(Date transferredOn) {
		this.transferredOn = transferredOn;
	}	
}
