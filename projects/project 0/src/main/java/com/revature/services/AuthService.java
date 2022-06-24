package com.revature.services;

import com.revature.dao.UserPostgresController;
import com.revature.exceptions.AuthException;
import com.revature.models.User;
import com.revature.util.Screens;
import com.revature.util.Validator;

import java.io.IOException;

import com.revature.dao.UserDao;

public class AuthService {
	
	private UserDao ud = new UserPostgresController();
	
	public User login(String username, String password) throws AuthException{

		if(!Validator.validateUsername(username) || !Validator.validatePassword(password)) {
			System.out.println("Empty values were given. No empty values.");
			try {
				Screens.loginScreen();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// Calling getByUserName from the UserPostgresController. This will perform logic that will retrieve the user from the database if one matches the username.
		User u = ud.getByUsername(username); // UserPostgresController needs to be set up.
		
		if(u.getId() == null || !u.getPassword().equals(password)) {
			throw new AuthException();
		}
		return u;
	}
}
