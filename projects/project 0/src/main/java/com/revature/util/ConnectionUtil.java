package com.revature.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {

	private static Connection connection;
	
	public static Connection getConnection() throws SQLException, IOException{
		// Creating a new Properties object that represents a persistent set of properties.
		Properties props = new Properties();
		// Creating a new class loader object to load a class. Using Thread to target the current thread and then gets the context class loader of the thread.
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		// Creating a stream to load in the values from the file
		props.load(loader.getResourceAsStream("connection.properties"));
		
		// Setting variables as the retrieved values
		String url = props.getProperty("url");
		String username = props.getProperty("username");
		String password = props.getProperty("password");
		
		// Checking if a connection is not instantiated or is closed. 
		if(connection == null || connection.isClosed()) {
			// Retrieves the connection to the database sending the parameters to access the DB.
			DriverManager.getConnection(url, username, password);
		}
		
		return connection;
	}
}
