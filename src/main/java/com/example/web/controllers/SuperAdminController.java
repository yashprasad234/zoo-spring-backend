package com.example.web.controllers;

import java.util.HashMap;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.web.dto.ResponseDTO.MenuResponseDto;
import com.example.web.dto.ResponseDTO.UserDetailsDto;
import com.example.web.entities.User;
import com.example.web.services.JwtService;
import com.example.web.services.UserService;

@RequestMapping("/super")
@RestController
public class SuperAdminController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private JwtService jwtService;
	
	@PreAuthorize("hasRole('SUPERADMIN')")
	@PutMapping("/createadmin/{email}")
	public ResponseEntity<UserDetailsDto> makeAdmin(@PathVariable String email) {
		User existingUser = userService.getUserByUsername(email);
		if(existingUser != null) {
			if(existingUser.getRole().equals("ADMIN")) {
				existingUser.setRole("USER");
			} else {
				existingUser.setRole("ADMIN");
			}			
			return ResponseEntity.ok(modelMapper.map(existingUser, UserDetailsDto.class));
		}
		return ResponseEntity.ofNullable(null);
	}
	
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/me")
	public ResponseEntity<MenuResponseDto> me(@RequestHeader(value="Authorization") String authHeader) {
		Map<String, String> menu = new HashMap<>();
		menu.put("Home", "/");
		menu.put("Dashboard", "/dashboard");
		menu.put("Users", "/users");
		menu.put("Admins", "/admins");
		String token = authHeader.substring(7);
		String usernameFromToken = jwtService.extractUsername(token);
		User user = userService.getUserByUsername(usernameFromToken);
		UserDetailsDto mappedUser = modelMapper.map(user, UserDetailsDto.class);
		return ResponseEntity.ok(new MenuResponseDto(mappedUser, menu));
	}
}
