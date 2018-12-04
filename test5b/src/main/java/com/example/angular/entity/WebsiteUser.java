package com.example.angular.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class WebsiteUser {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String name;
	private String email;
	
	public long getId () {
		return this.id;
	}
	
	public void setId (long id) {
		this.id = id;
	}

	public String getName () {
		return this.name;
	}
	
	public void setName (String name) {
		this.name = name;
	}
	public String getEmail () {
		return this.email;
	}
	
	public void setEmail (String email) {
		this.email = email;
	}
	
}
