package com.example.web.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @Column(unique = true)
    private String email;

    private String password;
    
    private String role;

    // No-argument constructor
    User() {
    }

    public User(String email, String password) {
        this(email, password, "USER");
    }

    public User(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.setRole(role);
    }
    
    public String getEmail() {
        return this.email;
    }

    void setEmail(String newEmail) {
        this.email = newEmail;
    }

    public String getPassword() {
        return this.password;
    }

    void setPassword(String newPassword) {
        this.password = newPassword;
    }

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
