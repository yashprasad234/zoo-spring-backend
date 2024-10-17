package com.example.web.dto;

import java.util.List;

import com.example.web.entities.Zoo;

public class ZooDTO {

	public static class ZooInputs {
		private String name;

		private String location;

		private float area;
		
		private String description;

		private Integer userId;
		
		public ZooInputs() {
		}

		public ZooInputs(String name, String location, float area, String description, Integer userId) {
			super();
			this.name = name;
			this.location = location;
			this.area = area;
			this.description = description;
			this.userId = userId;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}

		public float getArea() {
			return area;
		}

		public void setArea(float area) {
			this.area = area;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public Integer getUserId() {
			return userId;
		}

		public void setUserId(Integer userId) {
			this.userId = userId;
		}
	}
	
	public static class ZooPaginationRes {
		private List<Zoo> zoos;
		private int pages;
		public ZooPaginationRes(List<Zoo> zoos, int pages) {
			super();
			this.zoos = zoos;
			this.pages = pages;
		}
		
		public ZooPaginationRes() {
		}

		public List<Zoo> getZoos() {
			return zoos;
		}

		public void setZoos(List<Zoo> zoos) {
			this.zoos = zoos;
		}

		public int getPages() {
			return pages;
		}

		public void setPages(int pages) {
			this.pages = pages;
		}
	}
}
