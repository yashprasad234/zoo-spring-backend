package com.example.web.dto;

public class AnimalDTO {
	public static class AnimalInputs {
		
		private Integer zooId;
		
		private String name;
		
		private String gender;
		
		private String species;
		
		private String habitat;
		
		private Integer userId;

		// Integer zooId, String name, String gender, Date dob, String species, String habitat, Integer userId
		public AnimalInputs(Integer zooId, String name, String gender, String species, String habitat, Integer userId) {
			this.zooId = zooId;
			this.name = name;
			this.gender = gender;
			this.species = species;
			this.habitat = habitat;
			this.userId = userId;
		}

		public AnimalInputs() {
		}

		public Integer getZooId() {
			return zooId;
		}

		public void setZooId(Integer zooId) {
			this.zooId = zooId;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public String getSpecies() {
			return species;
		}

		public void setSpecies(String species) {
			this.species = species;
		}

		public String getHabitat() {
			return habitat;
		}

		public void setHabitat(String habitat) {
			this.habitat = habitat;
		}

		public Integer getUserId() {
			return userId;
		}

		public void setUserId(Integer userId) {
			this.userId = userId;
		}
	}
}
