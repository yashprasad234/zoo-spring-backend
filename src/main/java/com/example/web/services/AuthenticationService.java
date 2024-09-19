package com.example.web.services;

import java.util.ArrayList;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.web.dto.RequestDTO.UserSignupInputs;
import com.example.web.entities.User;
import com.example.web.repositories.UserRepository;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    
    private final BCryptPasswordEncoder passwordEncoder;
    
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
        UserRepository userRepository,
        AuthenticationManager authenticationManager,
        BCryptPasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(UserSignupInputs input) {
        User user = new User(input.getUsername(), passwordEncoder.encode(input.getPassword()));
        return userRepository.save(user);
    }
    
    public User authenticate(UserSignupInputs input) {
    	User existingUser = userRepository.findByUsername(input.getUsername());
		if(passwordEncoder.matches(input.getPassword(), existingUser.getPassword())) {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					input.getUsername(),
					input.getPassword(),
					new ArrayList<>()
			));   		
		}
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						input.getUsername(),
						input.getPassword()
						)
				);
        return existingUser;
    }
}