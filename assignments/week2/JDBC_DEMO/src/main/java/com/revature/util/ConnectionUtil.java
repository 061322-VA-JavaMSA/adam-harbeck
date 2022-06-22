package com.revature.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
	// Singleton is a design pattern that ensures there is only one instance of a class
	// -- Useful for heavier resources
	/*
		-- Enforce via multiple conditions
			- private static instance of the singleton class
			- public static getter method to retrieve the same instance of the singleton.
			- private constructor creates a singleton class as the constructor cant be called outside of the class
	*/
	
	
	// Reuse 'c' instead of creating a new connection to the DB
	// Declaring a private static  variable. (constant variable only accessible in the class)
	private static Connection c;
	
	// The method for connecting to the DB using hard-coded variables
//	public static Connection getHardCodedConnection() throws SQLException{
//		
//		// The hard-coded values to connect to the DB.
//		String url = "";
//		String username = "";
//		String password = "";
//		
//		// Checks if connection is open, and opens one if it isn't
//		if (c == null || c.isClosed()) {
//			// Uses our Connection variable, then calls on DriverManager's method getConnection() which is sent in the url, username, and password of the db.
//			c = DriverManager.getConnection(url, username, password);
//		}
//		// Returns the connection 
//		return c;
//	}
	
	// The method for connecting to the DB using variables found in a file.
	public static Connection getConnectionFromFile() throws SQLException, IOException {
		// Opens a new file stream and collects the variables from the specified file.
		Properties prop = new Properties();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		prop.load(loader.getResourceAsStream("connection.properties"));
		
		// Sets String objects to the values found in the file by their key.
		String url = prop.getProperty("url");
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		
		System.out.println(url + " " + username + password);
		// Checks if connection is open, and opens one if it isn't
		if (c == null || c.isClosed()) {
			// Uses our Connection variable, then calls on DriverManager's method getConnection() which is sent in the url, username, and password of the db.
			c = DriverManager.getConnection(url, username, password);
		}
		
		return c;
	}
	
	// Figure out how to set ENV on Java for Mac
	// The method for connecting to the DB using environment variables.
	public static Connection getConnectionFromEnv() throws SQLException {
		// Can use System.getenv() if we set the environment variables or use a package that specifies a file of env variables
		String url = System.getenv("DB_URL");
		String username = System.getenv("DB_USER");
		String password = System.getenv("DB_PASS");
		
		if (c == null || c.isClosed()) {
			// Uses our Connection variable, then calls on DriverManager's method getConnection() which is sent in the url, username, and password of the db.
			c = DriverManager.getConnection(url, username, password);
		}
		
		return c;
	}
	
}
