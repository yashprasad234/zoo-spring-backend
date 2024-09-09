package com.example.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.web.dto.ResponseDTO.UserDetails;
import com.example.web.dto.ResponseDTO.UserEmailResponse;
import com.example.web.entities.User;
import com.example.web.services.UserService;

@RestController
public class UserDetailsController
{
	
	@Autowired
	private UserService userService;

	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/getuser/{email}")
	public UserDetails getuser(@PathVariable String email) {
		
		User fetchedUser = userService.getUserByEmail(email);
		UserDetails userDetails = modelMapper.map(fetchedUser, UserDetails.class);
		return userDetails;
	}
}
