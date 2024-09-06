 package com.example.web.controllers;
 
 import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;

import com.example.web.entities.User;
import com.example.web.services.UserService;
import com.example.web.dto.ResponseDTO.UserEmailResponse;
 
 @RestController
 public class SignupController {
	 @Autowired
	 private final UserService userService;

	 @Autowired
	 private final ModelMapper modelMapper;
	 // Constructor injection
	 public SignupController(UserService userService, ModelMapper modelMapper) {
		 this.userService = userService;
		 this.modelMapper = modelMapper;
	 }
	 
	 @PostMapping("/signup")	 
	 public ResponseEntity<UserEmailResponse> signup(@RequestBody User signupInput) {
		 User existingUser = userService.getUserByEmail(signupInput.getEmail());
		 if(existingUser != null) {
			 System.out.println("User already exists");
			 return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		 } else {
			 User newUser = userService.createUser(signupInput);
			 UserEmailResponse userDto = this.modelMapper.map(newUser, UserEmailResponse.class);
			 System.out.println("New user created successfully");
			 return new ResponseEntity<>(userDto, HttpStatus.CREATED);
		 }
	 }
 }