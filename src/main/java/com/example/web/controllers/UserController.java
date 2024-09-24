package com.example.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.web.customClasses.MyUserDetails;
import com.example.web.dto.RequestDTO.PasswordInputDto;
import com.example.web.entities.User;
import com.example.web.repositories.UserRepository;
import com.example.web.services.JwtService;
import com.example.web.services.UserService;


@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	 
	 @GetMapping("/forgotpassword")
	 public ResponseEntity<String> forgot(@RequestHeader(value="X-Email") String email) {
		 User existingUser = userService.getUserByUsername(email);
		 if(existingUser != null && !existingUser.getRole().matches("SUPERADMIN")) {
			 String token = jwtService.generateToken(modelMapper.map(existingUser, MyUserDetails.class));
			 return ResponseEntity.ok("Click on the link below to change your password: http://localhost:3000/forgotPassword/change?token="+token);
		 }		 
		 return ResponseEntity.status(400).body("User not found");
	 }
	 
	 @PreAuthorize("hasRole('USER')")
	 @PutMapping("/changePassword")
	 public ResponseEntity<String> changePassword(@RequestHeader(value="Authorization") String authHeader, @RequestBody PasswordInputDto input) {
		 String token = authHeader.substring(7);
		 String usernameFromToken = jwtService.extractUsername(token);
		 User user = userService.getUserByUsername(usernameFromToken);
		 if(user == null) {
			 return ResponseEntity.status(404).body("user not found");
		 }
		 String hashedPassword = passwordEncoder.encode(input.getPassword());
		 System.out.println("hashedPassword : " + hashedPassword);
		 user.setPassword(hashedPassword);
		 System.out.println(user.getPassword());
		 userRepository.save(user);
		 return ResponseEntity.ok("Password changed successfully");
	 }
}
