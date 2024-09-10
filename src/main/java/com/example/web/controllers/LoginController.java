 package com.example.web.controllers;
 
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.web.dto.RequestDTO.UserSignupInputs;
import com.example.web.dto.ResponseDTO.LoginResponse;
import com.example.web.entities.User;
import com.example.web.services.AuthenticationService;
import com.example.web.services.JwtService;

 @RestController
 public class LoginController {
	 
	 @Autowired
	 private ModelMapper modelMapper;
	 
	 @Autowired
	 private AuthenticationService authenticationService;
	 
	 @Autowired
	 private JwtService jwtService;
	 
	 @PostMapping("/login")
	 public ResponseEntity<LoginResponse> login(@RequestHeader(value="X-Email") String email, @RequestHeader(value="X-Password") String password) {
		 System.out.println("IN CONTROLLER ------ Username: " + email + ", password: " + password);
		 UserSignupInputs inputs = new UserSignupInputs(email, password);
		 
		 User authenticatedUser = authenticationService.authenticate(inputs);
		 System.out.println(authenticatedUser.getUsername());
		 System.out.println(authenticatedUser.getPassword());
		 System.out.println(authenticatedUser.getRole());
		 
		 UserDetails user = modelMapper.map(authenticatedUser, UserDetails.class);
		 
		 String jwtToken = jwtService.generateToken(user);
		 System.out.println("JWT ------ " + jwtToken);

	     LoginResponse loginResponse = new LoginResponse();
	     loginResponse.setToken(jwtToken);
	     loginResponse.setExpiresIn(jwtService.getExpirationTime());
		 
	     return ResponseEntity.ok(loginResponse);
	 }
 }
 