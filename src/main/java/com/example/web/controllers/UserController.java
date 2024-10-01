package com.example.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.web.dto.RequestDTO.PasswordInputDto;
import com.example.web.entities.User;
import com.example.web.repositories.UserRepository;
import com.example.web.services.JwtService;
import com.example.web.services.UserService;


@RestController
@RequestMapping({"/user", "/consumer"})
public class UserController
{
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	 
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
