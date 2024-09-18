package com.example.web.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.example.web.customClasses.MyUserDetails;
import com.example.web.entities.User;
import com.example.web.repositories.UserRepository;

@Component
public class ZooUserDetailservice implements UserDetailsService
{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public ZooUserDetailservice(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }
	
	@Override
	public MyUserDetails loadUserByUsername(String username) {
		User user = userRepository.findByUsername(username);
        if (user != null) {        	
        	return modelMapper.map(user, MyUserDetails.class);
        } else {
        	new Error("UsernameNotFoundException");
        	return null;
        }
	}

}
