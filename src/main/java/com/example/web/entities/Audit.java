package com.example.web.entities;

import java.util.Date;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class Audit {
	public Date createdOn;
	public Integer createdBy;
	public Date updatedOn;
	public Integer updatedBy;
	
	public Audit(Integer userId) {
		this.createdOn = new Date();
		this.createdBy = userId;
		this.updatedOn = new Date();
		this.updatedBy = userId;
	}
	
	public Audit() {
	}
	
	public Date getCreatedOn() {
		return createdOn;
	}
	
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
	public Integer getCreatedBy() {
		return createdBy;
	}
	
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	
	public Date getUpdatedOn() {
		return updatedOn;
	}
	
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	
	public Integer getUpdatedBy() {
		return updatedBy;
	}
	
	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}
	
}
