package com.revature.services;

import com.revature.dao.UserDao; 
import com.revature.dao.UserPostgres; 
import com.revature.exceptions.LoginException;
import com.revature.models.User;

public class AuthService {

	// Declares a type of UserDao as a new UserPostgres object. UserPostgres implements UserDao then overrides and defines the methods declared in UserDao.
	private UserDao ud = new UserPostgres();
	
	// Returns a type User. Takes in two parameters for authentication and throws the custom exception LoginException (found in the com.revature.exceptions package).
	public User login(String username, String pass) throws LoginException {
		
		// Checks if the user input was null and responds with an exception.
		if(username == null || pass == null) {
			// Throws the custom exception LoginException
			throw new LoginException();
		}
		// A new User is created with the value input from the user sent into this method.
		User u = ud.retrieveUserByUsername(username);
		
		// If no user has been retrieved/password doesn't match, throw exception
		if(u == null || !u.getPassword().equals(pass)) {
			throw new LoginException();
		}
		
		// Returns the user that was collected from the DB by using ud.retrieveUserByUsername() above. Which called on the UserPostgres method retrieveuserByUsername() to find the data
		return u;
	}
	
}
