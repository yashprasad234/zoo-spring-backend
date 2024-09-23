package com.example.web.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.web.entities.User;

/**
 * 
 */
public interface UserRepository extends CrudRepository<User, Integer> {
	User findByUsername(String username);
	User findById(int id);
	List<User> findAll();
}
