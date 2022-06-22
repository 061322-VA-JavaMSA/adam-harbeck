package com.revature;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import com.revature.services.AuthService;
import com.revature.services.UserService;



public class Driver {

	static Scanner scan;
	static AuthService authServ;
	static UserService userServ;
	static String headingSeparator = "\n==========================\n";
	static String breaker = "\n------------------------\n";

	
	
	public static void main(String[] args) {
		
		scan = new Scanner(System.in);
		authServ = new AuthService();
		userServ = new UserService();
		
		System.out.println(headingSeparator + "\n~ Welcome to the EV Shop ~\n" + headingSeparator);
		
		// Calls the method 'welcomeScreen" to show the beginning options
		welcomeScreen();
		
		
	}
	
	public static void welcomeScreen() {
		System.out.println("Would you like to:\n\n1. Login\n2. Register\n3. Exit");
		int option = scan.nextInt();
		
		switch(option) {
		case 1:
			loginScreen();
			break;
		case 2:
			registerScreen();
			break;
		case 3:
			exit();
			break;
		default:
			System.out.println(breaker + "\nYou have entered an invalid option. Choose an option 1 - 3.\n");
			welcomeScreen();
		}
	}
	
	public static void loginScreen() {
		System.out.println(breaker);
		
		// BufferedReader was user over Scanner. Scanner.next doesn't allow null values, and Scanner.nextLine jumps to the end.
		BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Username: ");
		String username = null;
		try {
			username = bufferReader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Password: ");
//		String password = scan.next();
		String password = null;
		try {
			password = bufferReader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Calling the login method in AuthService. passing in the username and password
		authServ.login(username, password);
	}
	
	public static void registerScreen() {
		
	}
	
	
	public static void exit() {
		System.out.println("Thanks for shopping at The EV Shop!");
	}


}
