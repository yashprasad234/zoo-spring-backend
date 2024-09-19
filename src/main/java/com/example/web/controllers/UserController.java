package com.example.web.controllers;

import java.util.HashMap;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.web.customClasses.MyUserDetails;
import com.example.web.dto.RequestDTO.UserSignupInputs;
import com.example.web.dto.ResponseDTO.LoginResponse;
import com.example.web.dto.ResponseDTO.MenuResponseDto;
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
		 
		 User authenticatedUser = authenticationService.authenticate(input);
		 MyUserDetails user = modelMapper.map(authenticatedUser, MyUserDetails.class);
		 String jwtToken = jwtService.generateToken(user);
	     LoginResponse loginResponse = new LoginResponse(jwtToken, jwtService.getExpirationTime(), modelMapper.map(authenticatedUser, UserDetailsDto.class));
		 
	     return ResponseEntity.ok(loginResponse);
	 }
	 
	 @PreAuthorize("hasRole('USER')")
	 @GetMapping("/me")
	 public ResponseEntity<MenuResponseDto> me(@RequestHeader(value="Authorization") String authHeader) {
		 Map<String, String> menu = new HashMap<>();
		 menu.put("Home", "/");
		 menu.put("Dashboard", "/dashboard");
		 menu.put("About", "/about");
		 String token = authHeader.substring(7);
		 String usernameFromToken = jwtService.extractUsername(token);
		 User user = userService.getUserByUsername(usernameFromToken);
		 UserDetailsDto mappedUser = modelMapper.map(user, UserDetailsDto.class);
		 return ResponseEntity.ok(new MenuResponseDto(mappedUser, menu));
	 }
	
	 @GetMapping("/{email}")
	 public ResponseEntity<UserDetailsDto> fetchUser(@PathVariable String email) {
		 User fetchedUser = userService.getUserByUsername(null);
		 UserDetailsDto response = modelMapper.map(fetchedUser, UserDetailsDto.class);
		 return ResponseEntity.ok(response);
	 }
	 
}
