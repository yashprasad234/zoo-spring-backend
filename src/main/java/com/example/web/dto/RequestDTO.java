package com.example.web.dto;

public class RequestDTO {
	public static class UserSignupInputs{		

		String username;
		String password;

		public UserSignupInputs() {
			super();
		}
		
		public UserSignupInputs(String username, String password) {
			super();
			this.username = username;
			this.password = password;
		}
		
		public String getUsername() {
			return this.username;
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
	
	public static class PasswordInputDto {
		private String password;

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public PasswordInputDto(String password) {
			this.password = password;
		}
		
		public PasswordInputDto() {
		}
		
	}
	
}
