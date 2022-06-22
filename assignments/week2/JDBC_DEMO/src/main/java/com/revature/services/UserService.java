package com.revature.services;

import java.util.List;

import com.revature.dao.UserDao;
import com.revature.dao.UserPostgres;
import com.revature.models.User;

public class UserService {
	// UserPostgres implements the interface UserDao, then overrides and defines the method functionality declared in UserDao in UserPostgres
	private UserDao ud = new UserPostgres(); 
	
	public List<User> getUsers() {
		// Calls UserPostgres' retrieveUsers method. This returns a List<User> from UserPostgres, which was retrieved from the DB.
		return ud.retrieveUsers(); 
	}
	
	public User createUser(User u) {
		// Calls UserPostgres' createUser method. Returns an ID of the newly created User from UserPostgres, which was added to the DB
		return ud.createUser(u); 
	}
	
}
