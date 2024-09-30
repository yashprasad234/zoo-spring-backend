package com.example.web.dto;

public class ZooDTO {

	public static class ZooInputs {
		private String city;
		
		private String state;
		
		private String country;
		
		private int capacity;
		
		private long inaugration;
		
		private int userId;

		public ZooInputs(String city, String state, String country, int capacity, long inaugration, int userId) {
			super();
			this.city = city;
			this.state = state;
			this.country = country;
			this.capacity = capacity;
			this.inaugration = inaugration;
			this.userId = userId;
		}

		public int getUserId() {
			return userId;
		}

		public void setUserId(int userId) {
			this.userId = userId;
		}

		public ZooInputs() {
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

		public int getCapacity() {
			return capacity;
		}

		public void setCapacity(int capacity) {
			this.capacity = capacity;
		}

		public long getInaugration() {
			return inaugration;
		}

		public void setInaugration(long inaugration) {
			this.inaugration = inaugration;
		}

	}
	
}
