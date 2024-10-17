package com.example.web.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.web.entities.Animal;

/**
 * 
 */
public interface AnimalRepository extends CrudRepository<Animal, Integer> {
	List<Animal> findAll();
	Animal findById(int id);
}
