 package com.example.web.controllers;
 
 import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;

import com.example.web.entities.User;
import com.example.web.services.UserService;
import com.example.web.dto.ResponseDTO.UserEmailAndIdResponse;

 @RestController
 public class LoginController {
	 
	 @Autowired
	 private final UserService userService;
	 
	 @Autowired
	 private final ModelMapper modelMapper;
	 
	 // Constructor injection
	 public LoginController(UserService userService, ModelMapper modelMapper) {
		 this.userService = userService;
		 this.modelMapper = modelMapper;
	 }
	 
	 @PostMapping("/login")
	 public ResponseEntity<UserEmailAndIdResponse> login(@RequestHeader(value="X-Email") String email, @RequestHeader(value="X-Password") String password) {
		 User fetchUser = userService.getUserByEmail(email);
		 if(fetchUser != null) {
			 if(fetchUser.getPassword().equals(password)) {
				 UserEmailAndIdResponse userDto = this.modelMapper.map(fetchUser, UserEmailAndIdResponse.class);
				 System.out.println("Logged In!");
				 return new ResponseEntity<>(userDto, HttpStatus.OK);
			 } 
			 System.out.println("Incorrect password");
			 return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		 }
		 System.out.println("User not found");
		 return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	 }
 }
 