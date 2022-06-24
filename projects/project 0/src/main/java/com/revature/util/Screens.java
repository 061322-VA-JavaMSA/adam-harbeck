package com.revature.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import com.revature.exceptions.AuthException;
import com.revature.models.User;
import com.revature.services.AuthService;
import com.revature.services.UserService;

public class Screens {
	static Scanner scan = new Scanner(System.in);
	static String breaker = "\n------------------------\n";
	static AuthService authServ = new AuthService();
	static UserService userServ = new UserService();
	static BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));
	
	public static void welcomeScreen() {
		System.out.println("Would you like to:\n\n1. Login\n2. Register\n3. Exit");
		int option = scan.nextInt();
		
		switch(option) {
		case 1:
			try {
				loginScreen();
			} catch (IOException e) {

				e.printStackTrace();
			}
			break;
		case 2:
			try {
				registerScreen();
			} catch (IOException e) {

				e.printStackTrace();
			}
			break;
		case 3:
			exit();
			break;
		default:
			System.out.println(breaker + "\nYou have entered an invalid option. Choose an option 1 - 3.\n");
			welcomeScreen();
		}
	}
	
	public static void loginScreen() throws IOException{
		System.out.println(breaker);
		
		// BufferedReader was user over Scanner. Scanner.next doesn't allow null values, and Scanner.nextLine jumps to the end.
		System.out.println("Username: ");
		String username = null;
		username = bufferReader.readLine();
		System.out.println("Password: ");
		String password = null;
		password = bufferReader.readLine();

		User u = null;
		// Calling the login method in AuthService. passing in the username and password
		try {
			u = authServ.login(username, password);
		} catch (AuthException e) {
			e.printStackTrace();
			
			System.out.println("Incorrect username or password");

			loginScreen();
		}
		

//		switch(u.getRoleId()) {
//		case 1:
//			// This will go to the CustomerScreen
//			break;
//		case 2:
//			// This will go to the EmployeeScreen
//			break;
//		case 3:
//			// This will go to the ManagerScreen
//			break;
//		}
		
	}
	
	public static void registerScreen() throws IOException{
		System.out.println(breaker);
		
		/*
		 	Capturing:
			 	First name
			 	Last name
			 	Username
			 	Password
		 */
		User u = new User();
		System.out.println("What is your first name?");
		u.setFirstName(bufferReader.readLine());

		System.out.println("What is your last name?");
		u.setLastName(bufferReader.readLine());
		System.out.println("Enter a username:");
		u.setUsername(bufferReader.readLine());
		System.out.println("Enter a password");
		u.setPassword(bufferReader.readLine());
		System.out.println("Enter your password again");
		String password2 = bufferReader.readLine();
		
		
		userServ.createUser(u);
	}
	
	public static void customerScreen() {
		
	}
	
	public static void managerScreen() {
		
	}
	
	public static void employeeScreen() {
		
	}
	
	
	public static void exit() {
		System.out.println("Thanks for shopping at The EV Shop!");
	}
}
