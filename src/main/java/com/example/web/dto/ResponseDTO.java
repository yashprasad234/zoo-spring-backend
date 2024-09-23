package com.example.web.dto;

import java.time.LocalDateTime;

public class ResponseDTO {
	
	public static class UserDetailsDto {
		Integer id;
		String username;
		String role;
		
		public String getUsername() {
			return this.username;
		}
		
		public void setUsername(String username) {
			this.username = username;
		}

		public Integer getId() {
			return this.id;
		}
		
		public void setId(Integer id) {
			this.id = id;
		}
		
		public String getRole() {
			return this.role;
		}
		
		public void setRole(String role) {
			this.role = role;
		}
		
		public UserDetailsDto() {
			super();
		}
		
		public UserDetailsDto(Integer id, String username, String role) {
			this.username = username;
			this.id = id;
			this.role = role;
		}
		
	}
	
	public static class LoginResponse {
		private String token;		
		private long expiresIn;		
		private UserDetailsDto userDetails;
		
		public LoginResponse(String token, long expiresIn, UserDetailsDto userDetails) {
			this.token = token;
			this.expiresIn = expiresIn;
			this.userDetails = userDetails;
		}

		public LoginResponse() {
			super();
		}

		public UserDetailsDto getUserDetails() {
			return userDetails;
		}

		public void setUserDetails(UserDetailsDto userDetails) {
			this.userDetails = userDetails;
		}
		
		public String getToken() {
			return this.token;
		}
		
		public void setToken(String token) {
			this.token = token;
		}

		public long getExpiresIn() {
			return this.expiresIn;
		}

		public void setExpiresIn(long expiresIn) {
			this.expiresIn = expiresIn;
		}
	}
	
	public static class ErrorResponse {
	    private String message;
	    private LocalDateTime timestamp;

	    public ErrorResponse() {
	    }
	    
	    public ErrorResponse(String message) {
	        this.message = message;
	        this.timestamp = LocalDateTime.now();
	    }

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public LocalDateTime getTimestamp() {
			return timestamp;
		}

		public void setTimestamp(LocalDateTime timestamp) {
			this.timestamp = timestamp;
		}
	    
	}
}
