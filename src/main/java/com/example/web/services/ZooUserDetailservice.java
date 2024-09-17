package com.example.web.services;

import java.util.function.Supplier;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
	public MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
                .orElseThrow(new Supplier<UsernameNotFoundException>() {
                    public UsernameNotFoundException get() {
                        return new UsernameNotFoundException("User not found");
                    }
                });
		return modelMapper.map(user, MyUserDetails.class);
	}

}
