package com.revature.dao;

import java.util.List;
import com.revature.models.User;

public interface UserDao {
	// Should only handle data access logic
	// Declaring the methods to be overridden by our service logic class for Users.
	User createUser(User u);
	User retrieveUserById(int id);
	List<User> retrieveUsers();
	User retrieveUserByUsername(String username);
	boolean updateUser(User u);
	boolean deleteUserById(int id);
	
}
