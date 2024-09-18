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
//    	System.out.println("In Authenticate function");
    	User existingUser = userRepository.findByUsername(input.getUsername());
//    	System.out.println("Input username: " + input.getUsername() + ", db Username: " + existingUser.getUsername());
//    	System.out.println("Input password: " + input.getPassword() + ", db password: " + existingUser.getPassword());
    	if(existingUser != null) {
//    		System.out.println("User found in db");
    		if(passwordEncoder.matches(input.getPassword(), existingUser.getPassword())) {
//    			System.out.println("passwords matched");
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
    	} else return null;
        return existingUser;
    }
}