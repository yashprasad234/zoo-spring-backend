package com.example.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.web.dto.RequestDTO.UserSignupInputs;
import com.example.web.entities.User;
import com.example.web.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public User createUser(User user) {
		return userRepository.save(user);
	}
	
	public User getUser(User user) {
		return userRepository.findByUsername(user.getUsername()); 
	}
	
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	public User findByUsernameAndUpdate(String username, UserSignupInputs input) {
		User existingUser = userRepository.findByUsername(username);
		if(existingUser != null) {
			existingUser.setUsername(input.getUsername());
			existingUser.setPassword(passwordEncoder.encode(input.getPassword()));
			User savedUser = userRepository.save(existingUser);
			return savedUser;
		} else {
			return null;
		}
	}
	
	public List<User> fetchAllUsers() {
		return userRepository.findAll();
	}
	
}
