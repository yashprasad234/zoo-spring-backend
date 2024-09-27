package com.example.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.web.entities.UserToken;
import com.example.web.repositories.UserTokenRepository;

@Service
public class UserTokenService {

	@Autowired
	private UserTokenRepository userTokenRepository;

	@Autowired
	private JwtService jwtService;

	public UserToken findUserTokenFromToken(String token) {
		UserToken existingToken = userTokenRepository.findByToken(token);
		return existingToken;
	}

	public UserToken createUserToken(String token, Integer userId) {
		UserToken newToken = new UserToken(token, userId);
		userTokenRepository.save(newToken);
		return newToken;
	}

	public String setTokenNotValid(UserToken token) {
		if(token == null) {
			return "No token found to update";
		}
		token.setIsValid(false);
		userTokenRepository.save(token);
		return "Token set to not valid";
	}


	public Boolean checkTokenIsValid(String token) {
		UserToken existingToken = userTokenRepository.findByToken(token);
		return existingToken != null && existingToken.getIsValid();
	}

	public void deleteExpiredTokens(Integer userId) {
		if(userId != null ) {			
			System.out.println("User token exists ");
			List<UserToken> userTokens = userTokenRepository.findByUserId(userId);
			if(userTokens.size() != 0) {
				for(UserToken tokenObj: userTokens) 
				{
					System.out.println("Previous login instances of the current user exist");

					if(jwtService.isTokenExpired(tokenObj.getToken())) 
					{
						System.out.println("Deleted token");
						userTokenRepository.deleteById(tokenObj.getId());
					}
				}
			}
			System.out.println("No user token found");
		}

	}
}
