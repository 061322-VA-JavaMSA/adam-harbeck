package com.revature.services;

import java.util.List;

import com.revature.dao.UserDao;
import com.revature.dao.UserPostgres;
import com.revature.models.User;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class UserService {
	// UserPostgres implements the interface UserDao, then overrides and defines the method functionality declared in UserDao in UserPostgres
	private UserDao ud = new UserPostgres(); 
	
	// Logger
	private static Logger log = LogManager.getLogger(UserService.class);
	
	public List<User> getUsers() {
		// Calls UserPostgres' retrieveUsers method. This returns a List<User> from UserPostgres, which was retrieved from the DB.
		return ud.retrieveUsers(); 
	}
	
	public User createUser(User u) {
		// Calls UserPostgres' createUser method. Returns an ID of the newly created User from UserPostgres, which was added to the DB
		User user = ud.createUser(u);
		// Doesn't send to the console
		log.info("user: " +  user );
		return ud.createUser(u); 
	}
	
}
