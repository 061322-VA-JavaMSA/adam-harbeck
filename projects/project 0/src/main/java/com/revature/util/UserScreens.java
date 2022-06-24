package com.revature.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;


import com.revature.models.User;
import com.revature.services.AuthService;
import com.revature.services.UserService;

public class UserScreens {

	static Scanner scan = new Scanner(System.in);
	static String breaker = "\n------------------------\n";
	static AuthService authServ = new AuthService();
	static UserService userServ = new UserService();
	static BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));
	
	public static void customerScreen(User u) throws SQLException, IOException {
		System.out.println("Customer Screen");
		System.out.println(breaker + "1: View available EVs \n2:View your EVs \n3: View remaining payments \n4: Update profile \n5: Logout \n6: Exit");
		int choice = scan.nextInt();
		
		switch(choice) {
		case 1:
			// View available EVs - those w/o a user_id - able to make an offer
			
			break;
		case 2:
			// View owned EVs - those with user's user_id
			
			break;
		case 3:
			// View remaining payments
			
			break;
		case 4:
			// Update a user

			boolean updated = userServ.updateUser(u); // Send in both user objects to compares and set
			
			if(!updated) {
				System.out.println("Profile wasn't updated");
				customerScreen(u);
			}
			
			System.out.println("User updated");
			
			break;
		case 5:
			// Logout
			Screens.welcomeScreen();
			break;
		case 6:
			// Exit the program
			Screens.exit();
			break;

		}
	}
	public static void employeeScreen(User u) throws SQLException, IOException {

		System.out.println("Employee Screen");
		System.out.println(breaker);
		System.out.println("1: Add an item \n2: Pending offers \n 3: Remove an item \n4: View payments \n5: Update profile \n6: Logout \n7: Exit");
		int choice = scan.nextInt();
		
		switch(choice) {
		case 1:
			// Add an item - create a new EV
			break;
		case 2:
			// Pending offers - accept or reject (could be the same if they accept and just have a)
			break;
		case 3:
			// Remove an item - remove an ev
			break;
		case 4:
			// View Payments - see all evs with a user_id
			break;
		case 5:
			// Update Profile - updates profile
			boolean updated = userServ.updateUser(u); // Send in both user objects to compares and set
			
			if(!updated) {
				System.out.println("Profile wasn't updated");
				customerScreen(u);
			}
			
			System.out.println("User updated");
			
			break;
		case 6:
			// Logout
			Screens.welcomeScreen();
			break;
		case 7:
			Screens.exit();
			break;
		default:
			System.out.println("Only enter numbers 1 - 7.");
			employeeScreen(u);
		}

	}
	
	
	// mainly for testing purposes.
	public static void managerScreen(User user) {
		System.out.println("Manager Screen");
		System.out.println(breaker);
		System.out.println("1: See all users\n2: Get user by id\n3: Delete a user \n4: Exit");
		int response = scan.nextInt();
		
		switch(response) {
		case 1:
			// This will get all users
			List<User> allUsers = userServ.getUsers();
			for(int i = 0; i < allUsers.size(); i ++ ) {
				System.out.println((i + 1) + ": " + allUsers.get(i));
			}
			break;
		case 2:
			// This will get a user by an id
			System.out.println(breaker);
			System.out.println("Enter us id:");
			String userId = null;
			try {
				userId = bufferReader.readLine();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			UUID id = UUID.fromString(userId) ;
			User u = userServ.getUserById(id);
			if(u.getId() == null) {
				System.out.println("No user found with that id");
				managerScreen(user);
			}
			System.out.println(u);
			
			break;
		case 3:
			System.out.println(breaker);
			System.out.println("Enter us id:");
			String userUUID = null;
			try {
				userUUID = bufferReader.readLine();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			UUID uid = UUID.fromString(userUUID) ;
			boolean didDelete = userServ.deleteUser(uid);
			if(!didDelete) {
				System.out.println("User wasn't deleted.");
				managerScreen(user);
			}
			System.out.println("User deleted");
			break;
		case 4:
			// This will exit
			Screens.exit();
			break;
		}
		
	}
	
}
