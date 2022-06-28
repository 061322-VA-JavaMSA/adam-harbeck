package com.revature.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import com.revature.models.EV;
import com.revature.models.User;
import com.revature.services.EVService;

public class EVScreens {

	
	static String breaker = "\n------------------------\n";
	static Scanner scan = new Scanner(System.in);
	static EVService evServ = new EVService();
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	
	public static void listScreen(List<EV> evs, String listType, User u) {
		System.out.println(breaker);
		System.out.println(listType);
		int i = 1;
		for(EV elec : evs) {
			System.out.println(i + ": " + elec);
			i++;
		}
		
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
	
	public static void createEVScreen() throws IOException{
		// Will ask for all the details
		EV newEv = new EV();
		System.out.println("What is the brand name?");
		newEv.setBrand(bf.readLine());
		System.out.println("What is the model?");
		newEv.setModel(bf.readLine());
		System.out.println("What is the range?");
		newEv.setRange(bf.read());
		System.out.println("What is the vehicle type id?");
		newEv.setVehicleTypeId(scan.nextInt());
		

		newEv = evServ.createEv(newEv);
		if(newEv == null) {
			System.out.println("Model is already in the shop.");
		} 
			
		
	}
	
	// Required when changing status to owned
	public static void updateEVScreen() {
		
	}
	
	public static void deleteEv() {
		System.out.println("Enter the ID of the EV you wish to remove:");
		try {
			UUID id = UUID.fromString(bf.readLine());
			boolean gotDeleted = evServ.removeEV(id);
			if(!gotDeleted) {
				System.out.println("User id was not found.");
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
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
