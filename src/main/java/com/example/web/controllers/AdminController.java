package com.example.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.web.dto.RequestDTO.UserSignupInputs;
import com.example.web.dto.ResponseDTO.UserDetailsDto;
import com.example.web.services.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/user/edit/{email}")
	public ResponseEntity<UserDetailsDto> editUser(@PathVariable String email, @RequestBody UserSignupInputs input) {
		UserDetailsDto updatedUser =  modelMapper.map(userService.findByUsernameAndUpdate(email, input), UserDetailsDto.class);
		return ResponseEntity.ok(updatedUser);
	}
	
}
