package com.example.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.web.services.UserService;
import com.example.web.dto.ResponseDTO.UserEmailResponse;
import com.example.web.entities.User;

@RestController
public class UserDetailsController
{
	
	@Autowired
	private UserService userService;

	
	@GetMapping("/getuser")
	public UserEmailResponse getuser(String email) {
		User fetchedUser = userService.getUserByEmail(email);
		UserEmailResponse userDetails = new UserEmailResponse(fetchedUser.getEmail());
		return userDetails;
	}
}
