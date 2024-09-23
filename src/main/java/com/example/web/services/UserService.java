package com.example.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
	
	public User findById(int id) {
		return userRepository.findById(id);
	}
	
	public User findByIdAndUpdateRole(int id, String role) {
		User existingUser = userRepository.findById(id);
		if(existingUser != null) {
			existingUser.setRole(role);
			User savedUser = userRepository.save(existingUser);
			return savedUser;
		} else {
			return null;
		}
	}
	
	public User findByIdAndUpdateUsername(int id, String username) {
		User existingUser = userRepository.findById(id);
		if(existingUser != null) {
			existingUser.setUsername(username);
			User savedUser = userRepository.save(existingUser);
			return savedUser;
		} else {
			return null;
		}
	}
	
	public User findByIdAndUpdatePassword(int id, String oldPassword, String password) {
		User existingUser = userRepository.findById(id);
		if(existingUser != null) {
			if(passwordEncoder.matches(oldPassword, existingUser.getPassword())) {
				String encodedPassword = passwordEncoder.encode(password);
				existingUser.setPassword(encodedPassword);
				User savedUser = userRepository.save(existingUser);
				return savedUser;
			}
		} 
		return null;
	}
	
	public List<User> fetchAllUsers() {
		return userRepository.findAll();
	}
	
}
