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
	@JoinColumn(name = "from_zoo_id", referencedColumnName = "id")
	private Zoo from_zoo;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "to_zoo_id", referencedColumnName = "id")
	private Zoo to_zoo;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "animal_id", referencedColumnName = "id")
	private Animal animal_id;

	private Date transferredOn;


	public AnimalHistory() {
	}

	public AnimalHistory(Zoo from_zoo, Zoo to_zoo, Animal animal_id) {
		this.animal_id = animal_id;
		this.from_zoo = from_zoo;
		this.to_zoo = to_zoo;
		this.transferredOn = new Date();
	}
	
	public Animal getAnimal_id() {
		return animal_id;
	}

	public void setAnimal_id(Animal animal_id) {
		this.animal_id = animal_id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Zoo getFrom_zoo() {
		return from_zoo;
	}

	public void setFrom_zoo(Zoo from_zoo) {
		this.from_zoo = from_zoo;
	}

	public Zoo getTo_zoo() {
		return to_zoo;
	}

	public void setTo_zoo(Zoo to_zoo) {
		this.to_zoo = to_zoo;
	}

	public Date getTransferredOn() {
		return transferredOn;
	}

	public void setTransferredOn(Date transferredOn) {
		this.transferredOn = transferredOn;
	}	
}
