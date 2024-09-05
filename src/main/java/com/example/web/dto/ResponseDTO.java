package com.example.web.dto;

public class ResponseDTO {
	public static class UserEmailResponse {
		String email;
		
		public UserEmailResponse(String email) {
			this.email = email;
		}
	}
	
	public static class UserEmailAndIdResponse {
		String email;
		Integer id;
		
		public UserEmailAndIdResponse(int id, String email) {
			this.email = email;
			this.id = id;
		}
	}
}
