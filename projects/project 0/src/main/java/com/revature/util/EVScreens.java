package com.revature.util;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.revature.models.EV;
import com.revature.models.User;
import com.revature.services.EVService;

public class EVScreens {
	/*
	  What screens are needed?
	  - Screen that displays a list of items (get all, get available, get owned)
	  - Screen that displays an item
	 
	 */
	
	static String breaker = "\n------------------------\n";
	static Scanner scan = new Scanner(System.in);
	static EVService evServ = new EVService();
	
	public static void listScreen(List<EV> evs, String listType, User u) {
		System.out.println(breaker);
		System.out.println(listType);
		int i = 1;
		for(EV elec : evs) {
			System.out.println(i + ": " + elec);
			i++;
		}
		
		// The user might want to choose an item. They would enter the number
		// then I use that number to get the item and send it to the item screen
		System.out.println("\n Choose a number or enter 00 to return to the menu.");
		int choice = scan.nextInt();
		switch(choice) {
			case 00:
				returnToScreen(u);
				break;
			default:
				if(choice > evs.size()) {
					System.out.println("Enter a number in the list." + breaker);
					listScreen(evs, listType, u);
				} else {
					// Will go to the item screen, sending in the item
					itemScreen(evs.get(choice - 1), u);
				}
		}
	}
	
	public static void itemScreen(EV elec, User u) {
		// Sending in the list can cause problems if multiple people are using the application
		// Shows a specific item
		System.out.println(elec);
		
		// Make an offer, return to screen
		System.out.println("1: Make an offer \n2: Return to list \n3: Return to the menu \n4: Exit");
		int choice = scan.nextInt();
		switch(choice) {
		case 1:
			// Goes to the offer screen
			break;
		case 2:
			// Returns to the list
			List<EV> available = evServ.availableEVs();
			EVScreens.listScreen(available, "Available EVs", u);
			break;
		case 3:
			//Return to the menu
			returnToScreen(u);
			break;
		case 4:
			// Exit
			Screens.exit();
			break;
		default:
			
		}
		
	}
	
	public static void createEVScreen() {
		// Will ask for all the details
		EV newEv = new EV();
		
	}
	
	public static void updateEVScreen() {
		
	}
	
	public static void returnToScreen(User u) {
		
		switch(u.getRoleId()) {
		case 1:
			try {
				UserScreens.customerScreen(u);
			} catch (SQLException | IOException e) {

				e.printStackTrace();
			}
			break;
		case 2:
			try {
				UserScreens.employeeScreen(u);
			} catch (SQLException | IOException e) {

				e.printStackTrace();
			}
			break;
		case 3:
			UserScreens.managerScreen(u);
			break;
		}
	}
	
}
