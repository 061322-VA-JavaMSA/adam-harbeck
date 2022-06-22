package com.revature;

import java.util.List;
import java.util.Scanner;

import com.revature.exceptions.LoginException;
import com.revature.models.Task;
import com.revature.models.User;
import com.revature.services.AuthService;
import com.revature.services.TaskService;
import com.revature.services.UserService;


public class Driver {
	
	// Declaring static variables
	static Scanner scan;
	static AuthService as;
	static UserService us;
	static TaskService ts;
	
	public static void main(String[] args) {

		// Defining the static variables
		scan = new Scanner(System.in);
		// Creates new instances of Auth and User services to be used
		as = new AuthService(); // Gives us the login() method
		us = new UserService(); // Gives us getUsers() and createUser()
		ts = new TaskService();
		
		// Declare and define String variables for catching user input
		String username = null;
		String password = null;
		
		// Asks the user for input and then sets the String variables declared above to value of the user input.
		System.out.println("Username");
		username = scan.nextLine();
		System.out.println("Password");
		password = scan.nextLine();

		
		// 
		try {
			// Calls AuthService and uses the Login methods to check the user credentials
			System.out.println(as.login(username, password)); 
		} catch (LoginException e) {
			System.out.println("Invalid Credentials");
			e.printStackTrace();
			
		}
		
		// Calls the getUsers method is UserService
		List<User> users = us.getUsers(); 
		for(User u : users ) {
			System.out.println(u);
		}
		
		
		System.out.println("Create username: ");
		String uname = scan.nextLine();
		System.out.println("Create password: ");
		String pass = scan.nextLine();
		// Declares and defines a new User object
		User userTBC = new User();
		// Sets the new User's username and password by calling on the User class' setters for username and password
		userTBC.setUsername(uname);
		userTBC.setPassword(pass);
		 // Calls the crateUser method in UserService and passes in the new User defined above as a parameter.
		System.out.println(us.createUser(userTBC));
		
		
		System.out.println("What user ID to get tasks?");
		int userId = Integer.parseInt(scan.nextLine());
		List<Task> tasksForUser = ts.getTaskByUserId(userId);
		for(Task t : tasksForUser) {
			System.out.println(t);
		}
		// Closes the Scanner resource we started at the beginning of the application
		scan.close();
		

	}

}
