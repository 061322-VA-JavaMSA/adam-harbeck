package com.revature.services;

import com.revature.dao.UserPostgresController;
import com.revature.util.Validator;
import com.revature.Driver;
import com.revature.dao.UserDao;

public class AuthService {
	
	private UserDao ud = new UserPostgresController();
	
	public void login(String username, String password) {

		if(!Validator.validateUsername(username) || !Validator.validatePassword(password)) {
			System.out.println("Empty values were given. No empty values.");
			Driver.loginScreen();
		} else {
			System.out.println("Got past");
		}
		
		// Calling getByUserName from the UserPostgresController. This will perform logic that will retrieve the user from the database if one matches the username.
		ud.getByUsername(username); // UserPostgresController needs to be set up.
	}
}
