package com.example.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.web.entities.User;
import com.example.web.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User createUser(User user) {
		return userRepository.save(user);
	}
	
	public User getUser(User user) {
		return userRepository.findByUsername(user.getUsername()).orElseThrow(null); 
	}
	
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username).orElseThrow(null);
	}
	
	public List<User> fetchAllUsers() {
		return userRepository.findAll();
	}
	
}
