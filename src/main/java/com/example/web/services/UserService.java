package com.example.web.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.web.entities.User;
import com.example.web.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public User createUser(User user) {
		return userRepository.save(user);
	}
	
	public User getUser(User user) {
		return userRepository.findByEmail(user.getEmail()); 
	}
	
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
}
