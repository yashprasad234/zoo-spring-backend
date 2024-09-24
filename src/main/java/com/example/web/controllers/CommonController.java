package com.example.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.web.customClasses.MyUserDetails;
import com.example.web.dto.RequestDTO.UserSignupInputs;
import com.example.web.dto.ResponseDTO.ErrorResponse;
import com.example.web.dto.ResponseDTO.LoginResponse;
import com.example.web.dto.ResponseDTO.UserDetailsDto;
import com.example.web.entities.User;
import com.example.web.services.JwtService;
import com.example.web.services.UserService;

@RestController
public class CommonController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody UserSignupInputs signupInput) {
		 User existingUser = userService.getUserByUsername(signupInput.getUsername());
		 if(existingUser != null) {
			 ErrorResponse errRes = new ErrorResponse("User already exists");
			 return new ResponseEntity<>(errRes, HttpStatus.CONFLICT);
		 }
		 String hashedPass = passwordEncoder.encode(signupInput.getPassword());
		 signupInput.setPassword(hashedPass);
		 User newUser = userService.createUser(signupInput);
		 return ResponseEntity.ok(modelMapper.map(newUser, UserDetailsDto.class));
	}
	

	 @PostMapping("/login")
	 public ResponseEntity<?> login(@RequestBody UserSignupInputs input) {
		 System.out.println("IN CONTROLLER ------ Username: " + input.getUsername() + ", password: " + input.getPassword());
		 User existingUser = userService.getUserByUsername(input.getUsername());
		 
		 if(existingUser == null) {
			 return new ResponseEntity<>(new ErrorResponse("User not found. Please Signup before logging in"), HttpStatus.NOT_FOUND);
		 } else {
			 if(!passwordEncoder.matches(input.getPassword(), existingUser.getPassword())) {
				 return new ResponseEntity<>(new ErrorResponse("Incorrect Password"), HttpStatus.UNAUTHORIZED);
			 }
			 MyUserDetails user = modelMapper.map(existingUser, MyUserDetails.class);
			 String jwtToken = jwtService.generateToken(user);
		     LoginResponse loginResponse = new LoginResponse(jwtToken, jwtService.getExpirationTime(), modelMapper.map(existingUser, UserDetailsDto.class));
		     return ResponseEntity.ok(loginResponse);
		 }
	 }
	 
	 @GetMapping("/me")
	 public ResponseEntity<UserDetailsDto> me(@RequestHeader(value="Authorization") String authHeader) {
		 if(authHeader == null) return ResponseEntity.ofNullable(null);
		 String token = authHeader.substring(7);
		 String usernameFromToken = jwtService.extractUsername(token);
		 User user = userService.getUserByUsername(usernameFromToken);
		 if(user == null) return ResponseEntity.ofNullable(null);
		 return ResponseEntity.ok(modelMapper.map(user, UserDetailsDto.class));
	 }

	 @GetMapping("/forgotpassword")
	 public ResponseEntity<String> forgot(@RequestHeader(value="X-Email") String email) {
		 User existingUser = userService.getUserByUsername(email);
		 if(existingUser != null && !existingUser.getRole().matches("SUPERADMIN")) {
			 String token = jwtService.generateToken(modelMapper.map(existingUser, MyUserDetails.class));
			 return ResponseEntity.ok("Click on the link below to change your password: http://localhost:3000/forgotPassword/change?token="+token);
		 }		 
		 return ResponseEntity.status(400).body("User not found");
	 }
}
