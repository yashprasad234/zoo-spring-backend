package com.example.web.controllers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	 public ResponseEntity<LoginResponse> login(@RequestHeader(value="X-Email") String email, @RequestHeader(value="X-Password") String password) {
		 System.out.println("IN CONTROLLER ------ Username: " + email + ", password: " + password);
		 UserSignupInputs inputs = new UserSignupInputs(email, password);
		 
		 User authenticatedUser = authenticationService.authenticate(inputs);
		 System.out.println("authenticated user username: "+authenticatedUser.getUsername());
		 System.out.println("authenticated user password:"+authenticatedUser.getPassword());
		 System.out.println("authenticated user role:"+authenticatedUser.getRole());
		 
		 MyUserDetails user = modelMapper.map(authenticatedUser, MyUserDetails.class);
		 
		 String jwtToken = jwtService.generateToken(user);
		 System.out.println("JWT ------ " + jwtToken);

	     LoginResponse loginResponse = new LoginResponse();
	     loginResponse.setToken(jwtToken);
	     loginResponse.setExpiresIn(jwtService.getExpirationTime());
		 
	     return ResponseEntity.ok(loginResponse);
	 }
	 
	 @GetMapping("/getusers")
	 public ResponseEntity<List<UserDetailsDto>> getusers() {
		 List<User> fetchedUsers = userService.fetchAllUsers();
		 List<UserDetailsDto> usersList = new ArrayList<>();
		 fetchedUsers.forEach(u -> usersList.add(modelMapper.map(u, UserDetailsDto.class)));
		 return ResponseEntity.ok(usersList);
	 }
	 
	 @GetMapping("/me")
	 public ResponseEntity<UserDetailsDto> me(@RequestHeader(value="Authorization") String authHeader) {
		 String token = authHeader.substring(7);
		 System.out.println("token:" + token);
		 String usernameFromToken = jwtService.extractUsername(token);
		 System.out.println("Username from token: " + usernameFromToken);
		 User user = userService.getUserByUsername(usernameFromToken);
		 UserDetailsDto mappedUser = modelMapper.map(user, UserDetailsDto.class); 
		 return ResponseEntity.ok(mappedUser);
	 }
	
	 @GetMapping("/{email}")
	 public ResponseEntity<UserDetailsDto> fetchUser(@PathVariable String email) {
		 User fetchedUser = userService.getUserByUsername(null);
		 UserDetailsDto response = modelMapper.map(fetchedUser, UserDetailsDto.class);
		 return ResponseEntity.ok(response);
	 }
}
