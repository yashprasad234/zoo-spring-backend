package com.example.web.controllers;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.web.customClasses.MyUserDetails;
import com.example.web.dto.RequestDTO.UserSignupInputs;
import com.example.web.dto.ResponseDTO.LoginResponse;
import com.example.web.dto.ResponseDTO.UserDetailsDto;
import com.example.web.entities.User;
import com.example.web.services.AuthenticationService;
import com.example.web.services.JwtService;
import com.example.web.services.UserService;


@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private ModelMapper modelMapper;
	   
	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UserService userService;
		 
	@PostMapping("/signup")
	public ResponseEntity<UserDetailsDto> signup(@RequestBody UserSignupInputs signupInput) {
		 System.out.println(signupInput.getUsername());
		 User existingUser = authenticationService.signup(signupInput);
		 return ResponseEntity.ok(modelMapper.map(existingUser, UserDetailsDto.class));
	}
	

	 @PostMapping("/login")
	 public ResponseEntity<LoginResponse> login(@RequestBody UserSignupInputs input) {
		 System.out.println("IN CONTROLLER ------ Username: " + input.getUsername() + ", password: " + input.getPassword());
		 
		 User authenticatedUser;
		try {
			authenticatedUser = authenticationService.authenticate(input);
		} catch (Exception e) {
			System.out.println("------>" + e.getMessage());
			return ResponseEntity.ok(new LoginResponse(e.getMessage()));
		}
		 MyUserDetails user = modelMapper.map(authenticatedUser, MyUserDetails.class);
		 String jwtToken = jwtService.generateToken(user);
	     LoginResponse loginResponse = new LoginResponse(jwtToken, jwtService.getExpirationTime(), modelMapper.map(authenticatedUser, UserDetailsDto.class));
		 
	     return ResponseEntity.ok(loginResponse);
	 }
	 
	 @PreAuthorize("hasRole('USER')")
	 @GetMapping("/me")
	 public ResponseEntity<UserDetailsDto> me(@RequestHeader(value="Authorization") String authHeader) {
		 if(authHeader == null) return ResponseEntity.ofNullable(null);
		 String token = authHeader.substring(7);
		 String usernameFromToken = jwtService.extractUsername(token);
		 User user = userService.getUserByUsername(usernameFromToken);
		 if(user == null) return ResponseEntity.ofNullable(null);
		 return ResponseEntity.ok(modelMapper.map(user, UserDetailsDto.class));
	 }
	 
}
