package com.example.web.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.web.entities.Animal;

/**
 * 
 */
public interface AnimalRepository extends CrudRepository<Animal, Integer> {
	List<Animal> findAll();
	Optional<Animal> findById(Integer id);
}
