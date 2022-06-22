package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class UserPostgres implements UserDao {

	// Overrides the declaration in UserDao
	@Override
	public User createUser(User u) {
		// Creates the SQL statement that will be sent to the DB
		String sql = "insert into users (username, password) values (? , ?) returning id;";
		
		// Try-catch block with resources. Calls on the ConnectionUtil methods to create a connection
		try(Connection c = ConnectionUtil.getHardCodedConnection()) {
			// Since we are taking in user input, a PreparedStatement is used.
			PreparedStatement ps = c.prepareStatement(sql);
			// Calls on the '?'s in the String sql above. The number is the number of the '?' and the second parameter is the value used.
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getPassword());
			
			// Creates a ResultSet that executes the preparedStatment(sql)
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				u.setId(rs.getInt("id"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return u;
	}

	// Overrides the declaration in UserDao
	@Override
	public User retrieveUserById(int id) {
		// Creates the SQL statement that will be sent to the DB
		String sql = "select * from users where id = ?;";
		User user = null;
		
		// Try-catch block with resources. Calls on the ConnectionUtil methods to create a connection
		try(Connection c = ConnectionUtil.getHardCodedConnection()){
			// Since we are taking in user input, a PreparedStatement is used.
			PreparedStatement ps = c.prepareStatement(sql);
			
			// this refers to the 1st ? in the sql String
			ps.setInt(1, id); 
			
			// Creates a ResultSet that executes the preparedStatment(sql)
			ResultSet rs = ps.executeQuery();
			
			// Checks if there are users, then creates and set the fields for a new User.
			if(rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}

	// Overrides the declaration in UserDao
	@Override
	public List<User> retrieveUsers() {

		// Creates the SQL statement that will be sent to the DB
		String sql = "select * from users;";
		List<User> users = new ArrayList<>();

		// Try-catch block with resources. Calls on the ConnectionUtil methods to create a connection
		try(Connection c = ConnectionUtil.getHardCodedConnection()){
			// Not taking in user input which is why this is fine as a Statement and not a PreparedStatement
			Statement s = c.createStatement();
			
			// Creates a ResultSet that executes the preparedStatment(sql)
			ResultSet rs = s.executeQuery(sql);
			
			// Checks if there are users, then creates and set the fields for a new User.
			while (rs.next()) {
				User u = new User();
				u.setId(rs.getInt("id"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				
				users.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return users;
	}

	// Overrides the declaration in UserDao
	@Override
	public User retrieveUserByUsername(String username) {
		// Creates the SQL statement that will be sent to the DB
		String sql = "select * from users where username  = ?;";
		User u = null;
		
		try (Connection c = ConnectionUtil.getHardCodedConnection();){
			// Since we are taking in user input, a PreparedStatement is used.
			PreparedStatement ps = c.prepareStatement(sql);
			
			// this refers to the 1st "?" in the sql string, allows to inject data
			ps.setString(1, username); 

			// Creates a ResultSet that executes the preparedStatment(sql)
			ResultSet rs = ps.executeQuery();
			
			// Checks if there are users, then creates and set the fields for a new User.
			while(rs.next()) {
				u = new User();
				u.setId(rs.getInt("id"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		return u;
		
	}

	// Overrides the declaration in UserDao
	@Override
	public boolean updateUser(User u) {
		// Creates the SQL statement that will be sent to the DB
		String sql = "update users set username = ?, password = ? where id = ?;";
		// Tracks if the rows have changed
		int rowsChanged = -1;
		
		// Try-catch block with resources. Calls on the ConnectionUtil methods to create a connection
		try(Connection c = ConnectionUtil.getHardCodedConnection()){
			// Since we are taking in user input, a PreparedStatement is used.
			PreparedStatement ps = c.prepareStatement(sql);
			
			// Sets the value of the '?'s in the String sql to the value of the second parameter
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getPassword());
			ps.setInt(3, u.getId());
			
			// Executes the statement and updates the table based on the sent values. Updates rowsChanged value
			rowsChanged = ps.executeUpdate();
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		// Checks the value of rowsChanged
		if(rowsChanged < 1) {
			return false;
		}
		return true;
	}

	// Overrides the declaration in UserDao
	@Override
	public boolean deleteUserById(int id) {
		// Creates the SQL statement that will be sent to the DB
		String sql = "delete from users where id = ?;";
		int rowsChanged = -1;
		
		// Try-catch block with resources. Calls on the ConnectionUtil methods to create a connection
		try(Connection c = ConnectionUtil.getHardCodedConnection()){
			// Since we are taking in user input, a PreparedStatement is used.
			PreparedStatement ps = c.prepareStatement(sql);
			// Sets the '?' in the String sql above to the value of the second parameter
			ps.setInt(1, id);
			
			rowsChanged = ps.executeUpdate();
			
		} catch (SQLException e) {

			e.printStackTrace();
		}

		if(rowsChanged < 1) {
			return false;
		}
		return true;
		

	}

}
