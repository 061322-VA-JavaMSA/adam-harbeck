package com.revature;

import com.revature.util.Screens;



public class Driver {
	static String headingSeparator = "\n==========================\n";


	public static void main(String[] args) {
		
		System.out.println(headingSeparator + "\n~ Welcome to the EV Shop ~\n" + headingSeparator);
		// Calls the method 'welcomeScreen" to show the beginning options
		Screens.welcomeScreen();
		
	}

}
