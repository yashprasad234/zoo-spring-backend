package com.example.web.dto;

public class ResponseDTO {
	public static class UserEmailResponse {
		String username;
		
		public String getUsername() {
			return this.username;
		}
		
		public void setUsername(String username) {
			this.username = username;
		}
		
		public UserEmailResponse() {
			super();
		}
		
		public UserEmailResponse(String username) {
			this.username = username;
		}
	}
	
	public static class UserDetails {
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
		
		public UserDetails() {
			super();
		}
		
		public UserDetails(Integer id, String username, String role) {
			this.username = username;
			this.id = id;
			this.role = role;
		}
		
	}
	
	public static class UserEmailAndIdResponse {
		String username;
		Integer id;
		
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
		
		public UserEmailAndIdResponse() {
			super();
		}
		
		public UserEmailAndIdResponse(Integer id, String username) {
			this.username = username;
			this.id = id;
		}
	}
}
