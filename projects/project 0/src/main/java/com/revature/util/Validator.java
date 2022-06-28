package com.revature.util;


public class Validator {
	
	
	public static boolean validateUsername(String uname) {
		if(uname.isEmpty()) {
			return false;
		} 

		return true;
	}
	
	public static boolean validatePassword(String pass) {
		if(pass.isEmpty()) {
			return false;
		}

		return true;
	}
	
	public static boolean validateName(String first, String last) {
		
		if(!first.matches("^[a-zA-Z]+$") || !last.matches("^[a-zA-Z]+$")) {
			return false;
		}
		
		return true;
	}
	

	
}
