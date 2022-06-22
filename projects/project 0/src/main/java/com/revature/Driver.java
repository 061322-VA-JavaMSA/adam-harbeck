package com.revature;

import java.util.Scanner;

import com.revature.services.AuthService;
import com.revature.services.UserService;
import com.revature.util.Validator;

public class Driver {

	static Scanner scan;
	static AuthService authServ;
	static UserService userServ;
	
	static String user = "u$er1";
	static String pass = "userPass";
	
	public static void main(String[] args) {
		
		scan = new Scanner(System.in);
		authServ = new AuthService();
		userServ = new UserService();
		
		System.out.println("==========================\n" + "\n~ Welcome to the EV Shop ~\n" + "\n==========================\n");
		
		// Calls on the static method 'welcomeScreen()' to display the welcome screen
		welcomeScreen();
		


		
	}
	
	public static void welcomeScreen() {
		System.out.println("Would you like to:\n\n1. Login\n2. Register\n3. Exit");
		int option = scan.nextInt();
		
		
		switch(option) {
		case 1:
			login();
			break;
		case 2:
			register();
			break;
		case 3:
			exit();
			break;
		default:
			System.out.println("\n------------------------\n\nYou have entered an invalid option. Choose an option 1 - 3.\n");
			welcomeScreen();
		}
		
		
	}
	
	public static void login() {
		System.out.println("Login");
		
		System.out.println("-------------------\nUsername: ");
		String username = scan.nextLine();
		Validator.validateUsername(username);
		System.out.println("Password: ");
		String password = Validator.validatePassword(scan.nextLine());
		System.out.println("Username: " + username + "\nPassword: " + password);
	}
	
	public static void register() {
		System.out.println("Register");
	}
	
	public static void exit() {
		System.out.println("Thanks for shopping at The EV Shop!");
	}


}
