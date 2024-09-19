package com.example.web.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.web.dto.ResponseDTO.MenuResponseDto;
import com.example.web.dto.ResponseDTO.UserDetailsDto;
import com.example.web.entities.User;
import com.example.web.services.JwtService;
import com.example.web.services.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private JwtService jwtService;

	@PreAuthorize("hasRole('ADMIN') or hasRole('SUPERADMIN')")
	@GetMapping("/getusers")
	 public ResponseEntity<List<UserDetailsDto>> getusers() {
		 List<User> fetchedUsers = userService.fetchAllUsers();
		 List<UserDetailsDto> usersList = new ArrayList<>();
		 fetchedUsers.forEach(u -> usersList.add(modelMapper.map(u, UserDetailsDto.class)));
		 return ResponseEntity.ok(usersList);
	 }
	
	 @PreAuthorize("hasRole('USER')")
	 @GetMapping("/me")
	 public ResponseEntity<MenuResponseDto> me(@RequestHeader(value="Authorization") String authHeader) {
		 Map<String, String> menu = new HashMap<>();
		 menu.put("Home", "/");
		 menu.put("Dashboard", "/dashboard");
		 menu.put("Users", "/users");
		 String token = authHeader.substring(7);
		 String usernameFromToken = jwtService.extractUsername(token);
		 User user = userService.getUserByUsername(usernameFromToken);
		 UserDetailsDto mappedUser = modelMapper.map(user, UserDetailsDto.class);
		 return ResponseEntity.ok(new MenuResponseDto(mappedUser, menu));
	 }
}
