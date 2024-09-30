package com.example.web.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.web.entities.Zoo;

/**
 * 
 */
public interface ZooRepository extends CrudRepository<Zoo, Integer> {
	
}
