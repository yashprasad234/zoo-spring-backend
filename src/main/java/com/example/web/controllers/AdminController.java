package com.example.web.controllers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.web.dto.ResponseDTO.UserDetailsDto;
import com.example.web.entities.User;
import com.example.web.services.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;

	@PreAuthorize("hasRole('ADMIN') or hasRole('SUPERADMIN')")
	@GetMapping("/getusers")
	 public ResponseEntity<List<UserDetailsDto>> getusers() {
		 List<User> fetchedUsers = userService.fetchAllUsers();
		 List<UserDetailsDto> usersList = new ArrayList<>();
		 fetchedUsers.forEach(u -> usersList.add(modelMapper.map(u, UserDetailsDto.class)));
		 return ResponseEntity.ok(usersList);
	 }
}
