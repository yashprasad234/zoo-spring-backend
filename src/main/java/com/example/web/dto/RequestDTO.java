package com.example.web.dto;

public class RequestDTO {
	public static class UserSignupInputs{		

		String username;
		String password;

		public UserSignupInputs() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		public UserSignupInputs(String username, String password) {
			super();
			this.username = username;
			this.password = password;
		}
		
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
	}
}
