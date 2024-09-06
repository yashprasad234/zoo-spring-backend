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
		
		public void setId(int id) {
			this.id = id;
		}
		
		public UserEmailAndIdResponse() {
			super();
		}
		
		public UserEmailAndIdResponse(int id, String email) {
			this.email = email;
			this.id = id;
		}
	}
}
