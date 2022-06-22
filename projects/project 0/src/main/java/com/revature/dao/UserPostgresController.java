package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class UserPostgresController implements UserDao{

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUserById(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getByUsername(String username) {
		String sql = "select * from users where username = ?";
		User retrievedUser = null;
		try(Connection connect = ConnectionUtil.getConnection()) {
			PreparedStatement prepedStatement = connect.prepareStatement(sql);
			
			prepedStatement.setString(1, username);
			
			ResultSet results = prepedStatement.executeQuery();
			
			while(results.next()) {
				// Set the fields from user
				retrievedUser = new User();
			}
			
			
		} catch (IOException i) {
			i.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public User createNewUser(User newUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateUser(User userToUpdate) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteUser(User uuid) {
		// TODO Auto-generated method stub
		return false;
	}

}
