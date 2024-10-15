package com.example.web.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.web.entities.Animal;

/**
 * 
 */
public interface AnimalRepository extends CrudRepository<Animal, Integer> {
}
