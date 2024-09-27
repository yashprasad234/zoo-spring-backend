package com.example.web.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_token")
public class UserToken {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;

	private Boolean isValid;
	
	@Column(unique = true)
	private String token;

	private Integer userId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserID(Integer userId) {
		this.userId = userId;
	}

	public UserToken(String token, Integer userId) {
		this.isValid = true;
		this.token = token;
		this.userId = userId;
	}

	public UserToken() {
	}

	
	
}
