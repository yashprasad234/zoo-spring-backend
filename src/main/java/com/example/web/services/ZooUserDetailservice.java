package com.example.web.services;

import java.util.function.Supplier;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.web.repositories.UserRepository;

@Component
public class ZooUserDetailservice implements UserDetailsService
{
	private final UserRepository userRepository;

    public ZooUserDetailservice(UserRepository userRepository) {
        this.userRepository = userRepository;
    } 
    
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return (UserDetails) userRepository.findByUsername(username)
                .orElseThrow(new Supplier<UsernameNotFoundException>() {
                    public UsernameNotFoundException get() {
                        return new UsernameNotFoundException("User not found");
                    }
                });
	}

}
