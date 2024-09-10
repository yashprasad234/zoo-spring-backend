 package com.example.web.controllers;
 
 import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.web.entities.User;
import com.example.web.services.UserService;
import com.example.web.dto.ResponseDTO.UserEmailResponse;
 
 @RestController
 public class SignupController {
	 @Autowired
	 private UserService userService;

	 @Autowired
	 private ModelMapper modelMapper;
	 
	 @Autowired
	 private BCryptPasswordEncoder bCryptPasswordEncoder;
	 
	 @PostMapping("/signup")	 
	 public ResponseEntity<UserEmailResponse> signup(@RequestBody User signupInput) {
		 User existingUser = userService.getUserByUsername(signupInput.getUsername());
		 if(existingUser != null) {
			 System.out.println("User already exists");
			 return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		 } else {
			 User newUser = new User(signupInput.getUsername(), bCryptPasswordEncoder.encode(signupInput.getPassword()));
			 userService.createUser(newUser);
			 UserEmailResponse userDto = modelMapper.map(newUser, UserEmailResponse.class);
			 System.out.println("New user created successfully");
			 return new ResponseEntity<>(userDto, HttpStatus.CREATED);
		 }
	 }
 }