package com.revature.dao;

import java.util.List;
import java.util.UUID;

import com.revature.models.User;

public interface UserDao {

	/*
	 - GET - Read
	 	- Get all users
	 	- Get user by id
	 - 	ADD - Create
	 	- Create a new user
	 - UPDATE - Update
	 	- Update a user by id
	 - DELETE - Delete
	 	- Delete a user by id
	 */
	
	List<User> getAllUsers();
	User getUserById(UUID uuid);
	User getByUsername(String username);
	User createNewUser(User newUser);
	boolean updateUser(User userToUpdate);
	boolean deleteUser(UUID uuid);
	
}
