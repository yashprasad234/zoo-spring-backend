 package com.example.web.controllers;
 
 import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.web.dto.RequestDTO.UserSignupInputs;
import com.example.web.dto.ResponseDTO.UserEmailResponse;
import com.example.web.entities.User;
import com.example.web.services.AuthenticationService;
 
 @RestController
 public class SignupController {

	 @Autowired
	 private ModelMapper modelMapper;
	    
	 @Autowired
	 private AuthenticationService authenticationService;
	 
	 @PostMapping("/signup")	 
	 public ResponseEntity<UserEmailResponse> signup(@RequestBody UserSignupInputs signupInput) {
		 User existingUser = authenticationService.signup(signupInput);
		 return ResponseEntity.ok(modelMapper.map(existingUser, UserEmailResponse.class));
	 }
 }