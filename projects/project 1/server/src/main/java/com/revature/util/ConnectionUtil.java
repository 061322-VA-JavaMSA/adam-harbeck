package com.revature.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {

	private static Connection connect;
	
	public static Connection getConnection() throws SQLException, IOException{

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Opens a new file stream and collects the variables from the specified file.
		Properties prop = new Properties();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		prop.load(loader.getResourceAsStream("connection.properties"));
		

		
		// Sets String objects to the values found in the file by their key.
		String url = prop.getProperty("url");
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		

		if(connect == null || connect.isClosed()) {

			connect = DriverManager.getConnection(url, username, password);
		}
		
		return connect;
	}
	
}
