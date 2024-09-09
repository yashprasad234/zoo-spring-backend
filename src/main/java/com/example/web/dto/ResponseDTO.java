package com.example.web.dto;

public class ResponseDTO {
	public static class UserEmailResponse {
		String email;
		
		public String getEmail() {
			return this.email;
		}
		
		public void setEmail(String email) {
			this.email = email;
		}
		
		public UserEmailResponse() {
			super();
		}
		
		public UserEmailResponse(String email) {
			this.email = email;
		}
	}
	
	public static class UserDetails {
		Integer id;
		String email;
		String role;
		
		public String getEmail() {
			return this.email;
		}
		
		public void setEmail(String email) {
			this.email = email;
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
		
		public UserDetails(Integer id, String email, String role) {
			this.email = email;
			this.id = id;
			this.role = role;
		}
		
	}
	
	public static class UserEmailAndIdResponse {
		String email;
		Integer id;
		
		public String getEmail() {
			return this.email;
		}
		
		public void setEmail(String email) {
			this.email = email;
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
		
		public UserEmailAndIdResponse(Integer id, String email) {
			this.email = email;
			this.id = id;
		}
	}
}
