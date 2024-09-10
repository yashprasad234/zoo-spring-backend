package com.example.web.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.web.entities.User;

/**
 * 
 */
public interface UserRepository extends CrudRepository<User, Integer> {
	Optional<User> findByUsername(String username);
}
