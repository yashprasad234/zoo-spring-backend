package com.example.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.web.dto.ResponseDTO.UserDetailsDto;
import com.example.web.entities.User;
import com.example.web.services.UserService;

@RequestMapping("/super")
@RestController
public class SuperAdminController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
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
	
//	@PreAuthorize("hasRole('SUPERADMIN')")
//	@DeleteMapping("/delete/{email}")
//	public ResponseEntity<T> delete(@PathVariable String email) {
//		
//	}
}
