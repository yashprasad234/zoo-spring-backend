 package com.example.web.controllers;
 
 import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.web.entities.User;
import com.example.web.services.UserService;
 
 @RestController
 public class SignupController {
	 private final UserService userService;

	 // Constructor injection
	 public SignupController(UserService userService) {
		 this.userService = userService;
	 }
	 
	 @PostMapping("/signup")	 
	 public String signup(@RequestBody User signupInput) {
		 User existingUser = userService.getUserByEmail(signupInput.getEmail());
		 if(existingUser != null) {
			 System.out.println("User already exists");
			 return "User already exists";
		 } else {
			 userService.createUser(signupInput);
			 System.out.println("New user created successfully");
			 return "New user created successfully";
		 }
	 }
 }