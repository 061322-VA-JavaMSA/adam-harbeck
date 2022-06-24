package com.revature.services;

import java.io.IOException;
import java.util.List;

import com.revature.dao.UserDao;
import com.revature.dao.UserPostgresController;
import com.revature.models.User;
import com.revature.util.Screens;
import com.revature.util.Validator;

public class UserService {

	private UserDao uDao = new UserPostgresController();
	
	public List<User> getUsers() {
		return uDao.getAllUsers();
	}
	
	public User createUser(User u) {
		
		boolean onlyLetters = Validator.validateName(u.getFirstName(), u.getLastName());
		if(!onlyLetters) {
			System.out.println("Names can only be letters");
			try {
				Screens.registerScreen();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return u;
	}
}
