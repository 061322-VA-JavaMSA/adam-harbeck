package com.revature.models;

import java.util.Objects;

/*
Encapsulation
// -- fields are private
// -- no-args constructor
// -- generated setter/getters to interact w/ private fields
// -- generated hashCode and .equals to compare user object
// -- generated toString() to print out to a console
*/
public class User {

	// Declaring the private variables the define our user
	private int id;
	private String username;
	private String password;
	
	// Created a no-args constructor that calls on super() the parent class.
	public User() {
		super();
		
	}
	
	// Getters and Setters that get and set the value of our private fields for encapsulation.
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	// Overrides the hashCode()
	@Override
	public int hashCode() {
		return Objects.hash(id, password, username);
	}
	// Overrides the .equals() so it can check our variables content
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return id == other.id && Objects.equals(password, other.password) && Objects.equals(username, other.username);
	}
	// Overrides toString to return a value of the user and not an unreadable string.
	@Override
	public String toString() {
		return "user [id=" + id + ", username=" + username + ", password=" + password + "]";
	}
	
	
	
}
