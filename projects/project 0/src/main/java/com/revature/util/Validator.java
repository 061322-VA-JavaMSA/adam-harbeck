package com.revature.util;


public class Validator {
	
	
	public static String validateUsername(String uname) {
		if(uname == null) {
			System.out.println("Username cannot be blank.");
			// Need to call the method that will show the login screen
		} 

		return uname;
	}
	
	public static String validatePassword(String pass) {
		if(pass == null) {
			System.out.println("Password cannot be blank.");
			// Call method to show login screen
		}

		return pass;
	}
	
}
