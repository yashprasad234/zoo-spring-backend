package com.example.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.web.entities.User;
import com.example.web.entities.UserToken;
import com.example.web.repositories.UserTokenRepository;

@Service
public class UserTokenService {

	@Autowired
	private UserTokenRepository userTokenRepository;
	
//	public User createUser(UserSignupInputs user) {
//		User newUser = new User(user.getUsername(), user.getPassword());
//		return userRepository.save(newUser);
//	}
	
	public UserToken createUserToken(String token, User user) {
		UserToken newToken = new UserToken(token, user);
		userTokenRepository.save(newToken);
		return newToken;
	}
	
	public UserToken setTokenNotValid(String token) {
		UserToken existingToken = userTokenRepository.findByToken(token);
		if(existingToken == null) {
			return null;
		}
		existingToken.setIsValid(false);
		userTokenRepository.save(existingToken);
		return existingToken;
	}


	public Boolean checkTokenIsValid(String token) {
		UserToken existingToken = userTokenRepository.findByToken(token);
		return existingToken != null && existingToken.getIsValid();
	}
	
}
