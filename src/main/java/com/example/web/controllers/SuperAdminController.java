package com.example.web.controllers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.web.dto.ResponseDTO.UserDetailsDto;
import com.example.web.dto.ZooDTO.ZooInputs;
import com.example.web.entities.User;
import com.example.web.entities.Zoo;
import com.example.web.services.UserService;
import com.example.web.services.ZooService;

@RequestMapping("/super")
@RestController
public class SuperAdminController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ZooService zooService;
	
	@PreAuthorize("hasRole('SUPERADMIN')")
	@GetMapping("/users")
	public ResponseEntity<List<UserDetailsDto>> getUsers() {
		List<User> fetchedUsers = userService.fetchAllUsers();
		 List<UserDetailsDto> usersList = new ArrayList<>();
		 fetchedUsers.forEach(u -> {
			 if(u.getRole().equals("USER")) {
				 usersList.add(modelMapper.map(u, UserDetailsDto.class));
			 }
		 });
		 return ResponseEntity.ok(usersList);
	}
	
	@PreAuthorize("hasRole('SUPERADMIN')")
	@GetMapping("/users/{email}")
	public ResponseEntity<UserDetailsDto> fetchUser(@PathVariable int id) {
		User fetchedUser = userService.findById(id);
		if(fetchedUser.getRole().equals("USER")) {
			UserDetailsDto response = modelMapper.map(fetchedUser, UserDetailsDto.class);
			return ResponseEntity.ok(response);
		}
		return ResponseEntity.ofNullable(null);
	}
	
	@PreAuthorize("hasRole('SUPERADMIN')")
	@GetMapping("/admins")
	public ResponseEntity<List<UserDetailsDto>> getAdmins() {
		List<User> fetchedUsers = userService.fetchAllUsers();
		List<UserDetailsDto> usersList = new ArrayList<>();
		fetchedUsers.forEach(u -> {
			if(u.getRole().equals("ADMIN")) {
				usersList.add(modelMapper.map(u, UserDetailsDto.class));
			}
		});
		return ResponseEntity.ok(usersList);
	}
	
	@PreAuthorize("hasRole('SUPERADMIN')")
	@GetMapping("/admins/{id}")
	public ResponseEntity<UserDetailsDto> fetchAdmin(@PathVariable int id) {
		User fetchedUser = userService.findById(id);
		if(fetchedUser.getRole().equals("ADMIN")) {
			UserDetailsDto response = modelMapper.map(fetchedUser, UserDetailsDto.class);
			return ResponseEntity.ok(response);
		}
		return ResponseEntity.ofNullable(null);
	}
	
	@PreAuthorize("hasRole('SUPERADMIN')")
	@PostMapping("/zoo/create")
	public ResponseEntity<?> createZoo(@RequestBody ZooInputs zooInput) {
		Zoo newZoo = zooService.createZoo(zooInput);
		return ResponseEntity.status(201).body(newZoo);
	}
}
