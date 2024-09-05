 package com.example.web.controllers;
 
 import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.web.entities.User;
import com.example.web.services.UserService;
 
 @RestController
 public class LoginController {
	 
	 private final UserService userService;
	 
	 // Constructor injection
	 public LoginController(UserService userService) {
		 this.userService = userService;
	 }
	 
	 @PostMapping("/login")
	 public String login(@RequestHeader(value="X-Email") String email, @RequestHeader(value="X-Password") String password) {
		 User fetchUser = userService.getUserByEmail(email);
		 System.out.println("hello");
		 if(fetchUser != null) {
			 if(fetchUser.getPassword().equals(password)) {
				 System.out.println("Logged In!");
				 return "Logged In!";
			 } 
			 System.out.println("Incorrect password");
			 return "Incorrect password";
		 }
		 System.out.println("User not found");
		 return "User not found";
	 }
 }
 