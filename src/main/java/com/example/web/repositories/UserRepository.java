package com.example.web.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.web.entities.User;

/**
 * 
 */
public interface UserRepository extends CrudRepository<User, Integer> {
	User findByUsername(String username);
}
