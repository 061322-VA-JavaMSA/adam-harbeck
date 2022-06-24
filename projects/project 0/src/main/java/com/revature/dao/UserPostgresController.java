package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.revature.models.User;
import com.revature.util.ConnectionUtil;
import com.revature.util.Screens;

public class UserPostgresController implements UserDao{

	@Override
	public List<User> getAllUsers() {
		String sql = "select * from shop_users;";
		List<User> users = new ArrayList<>();
		
		try(Connection connect = ConnectionUtil.getConnection()) {
			Statement statement = connect.createStatement();
			
			ResultSet results = statement.executeQuery(sql);
			
			while(results.next()) {
				User retrievedUser = new User();
				// Set the fields from user
				retrievedUser.setId(results.getObject("id", java.util.UUID.class));
				retrievedUser.setFirstName(results.getString("first_name"));
				retrievedUser.setLastName(results.getString("last_name"));
				retrievedUser.setUsername(results.getString("username"));
				retrievedUser.setPassword(results.getString("password"));
				retrievedUser.setRoleId(results.getInt("shop_role_id"));
				
				users.add(retrievedUser);
			}
			
		} catch (SQLException s) { 
			s.printStackTrace();
		} catch (IOException i) {
			i.printStackTrace();
		} 
		
		return users;
	}

	@Override
	public User getUserById(UUID uuid) {
		String sql = "select * from shop_users where id = ?;";
		User retrievedUser = new User();
		try(Connection connect = ConnectionUtil.getConnection()) {
			PreparedStatement preppedStatement = connect.prepareStatement(sql);
			preppedStatement.setObject(1, uuid);
			
			ResultSet results = preppedStatement.executeQuery();
			
			while(results.next()) {
				// Set the fields from user
				retrievedUser.setId(results.getObject("id", java.util.UUID.class));
				retrievedUser.setFirstName(results.getString("first_name"));
				retrievedUser.setLastName(results.getString("last_name"));
				retrievedUser.setUsername(results.getString("username"));
				retrievedUser.setPassword(results.getString("password"));
				retrievedUser.setRoleId(results.getInt("shop_role_id"));
			}
			
			
		} catch (IOException i) {
			i.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
			System.out.println("No user with that ID.");
			
			
		}
		

		return retrievedUser;
	}

	@Override
	public User getByUsername(String username) {
		String sql = "select * from shop_users where username = ?;";
		User retrievedUser = new User();
		try(Connection connect = ConnectionUtil.getConnection()) {
			PreparedStatement preppedStatement = connect.prepareStatement(sql);
			preppedStatement.setString(1, username);
			
			ResultSet results = preppedStatement.executeQuery();
			
			while(results.next()) {
				// Set the fields from user
				retrievedUser.setId(results.getObject("id", java.util.UUID.class));
				retrievedUser.setFirstName(results.getString("first_name"));
				retrievedUser.setLastName(results.getString("last_name"));
				retrievedUser.setUsername(results.getString("username"));
				retrievedUser.setPassword(results.getString("password"));
				retrievedUser.setRoleId(results.getInt("shop_role_id"));
			}
			
			
		} catch (IOException i) {
			i.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

		return retrievedUser;
	}

	@Override
	public User createNewUser(User newUser) {
		String sql = "insert into shop_users (id, first_name, last_name, username, password) values (?, ?, ?, ?, ?) returning id;";
		
		try(Connection connect = ConnectionUtil.getConnection()){
			PreparedStatement preppedStatement = connect.prepareStatement(sql);
			
			preppedStatement.setObject(1, newUser.getId());
			preppedStatement.setString(2, newUser.getFirstName());
			preppedStatement.setString(3, newUser.getLastName());
			preppedStatement.setString(4, newUser.getUsername());
			preppedStatement.setString(5, newUser.getPassword());
			
			ResultSet results = preppedStatement.executeQuery();
			
			if(results.next()) {
				System.out.println("Created user");
			}
			
		} catch(IOException i) {
			i.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
			
			System.out.println("User couldn't be added");
			Screens.welcomeScreen();
		}
		
		return newUser;
	}

	@Override
	public boolean updateUser(User userToUpdate) {
		String sql = "update shop_users set first_name = ?, last_name = ?, username = ?, password = ? where id = ?;";
		int rowChanged = -1;
		
		try(Connection connect = ConnectionUtil.getConnection()) {
			PreparedStatement preppedStatement = connect.prepareStatement(sql);
			
			preppedStatement.setString(1, userToUpdate.getFirstName());
			preppedStatement.setString(2, userToUpdate.getLastName());
			preppedStatement.setString(3, userToUpdate.getUsername());
			preppedStatement.setString(4, userToUpdate.getPassword());
			preppedStatement.setObject(5, userToUpdate.getId());
			
			rowChanged = preppedStatement.executeUpdate();
			
		} catch (SQLException | IOException e) {
			e.printStackTrace();

		}
		
		if(rowChanged < 1) {
			return false;
		}
		
		return true;
	}

	@Override
	public boolean deleteUser(UUID uuid) {
		String sql = "delete from shop_users where id = ?;";
		int rowChanged = -1;
		
		try(Connection connect = ConnectionUtil.getConnection()){
			PreparedStatement preppedStatement = connect.prepareStatement(sql);
			
			preppedStatement.setObject(1, uuid);
			
			rowChanged = preppedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if(rowChanged < 1) {
			return false;
		}
		
		return true;
	}

}
