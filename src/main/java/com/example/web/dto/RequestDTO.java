package com.example.web.dto;

public class RequestDTO {
	public static class UserSignupInputs{		

		String email;
		String password;

		public UserSignupInputs() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		public UserSignupInputs(String username, String password) {
			super();
			this.email = username;
			this.password = password;
		}
		
		public String getEmail() {
			return email;
		}
		public void setEmail(String username) {
			this.email = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
	}
}
