package com.revature.util;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.revature.models.EV;
import com.revature.models.Offer;
import com.revature.models.PendingOffers;
import com.revature.models.User;
import com.revature.services.OfferService;

public class OfferScreen {

	static String breaker = "\n\n\n\n------------------------\n";
	static OfferService offServ = new OfferService();
	static Scanner scan = new Scanner(System.in);
	
	public static void makeOffer(EV elec, User u) {
		System.out.println(breaker );
		
		// Can get the price/offer from the offers based on EV id - select * from offers where ev_id = 
		Offer o = offServ.getHighestOffer(elec.getId());
		System.out.println("Highest bid is currently: " + o.getOffer() + "\n\n");
		System.out.println("\nWhat is your offer? (Must be greater than current bid)");
		// Take the user input - validate that it is higher - create a new offer if it is.
		double offer = scan.nextDouble();
		
		if(offer < o.getOffer()) {
			System.out.println("Offer cannot be less than " + o.getOffer());
			EVScreens.itemScreen(elec, u);
		} else {
			
			offServ.createOffer(elec, u, offer);
		}
		
		EVScreens.itemScreen(elec, u);
		
	}
	
	public static void getOffers(User u) {
		System.out.println(breaker + "\n");
		List<PendingOffers> offers = offServ.getOffers();
		int i = 1;
		for(PendingOffers po : offers) {
			String toShow = "Id: " + po.getOfferId() + " | Brand: " + po.getBrand() + " | Model: " + po.getModel() + " | Offer: " + po.getOffer() + " | User: " + po.getUsername(); 
			System.out.println(i + ": " + toShow);
			i++;
		}
		
		System.out.println("\n\n\nEnter an number for options or enter 00 to return.");
		int choice = scan.nextInt();
		
		switch(choice) {
		case 00:
			try {
				UserScreens.employeeScreen(u);
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			if(choice > offers.size()) {
				System.out.println("Enter a number in the list.");
				getOffers(u);
			} else {
				System.out.println("\nWould you like to accept or reject this offer?\n1: Accept \n2: Reject \n3: To list");
				int decision = scan.nextInt();
				switch(decision) {
				case 1: 
					offServ.acceptOffer(offers.get(choice - 1).getOfferId());
					getOffers(u);
					break;
				case 2:
					offServ.rejectOffer(offers.get(choice -1).getOfferId());
					getOffers(u);
					break;
				case 3:
					getOffers(u);
					break;
				default:
					System.out.println("Option not recognized. Returning to list");
					getOffers(u);
				}
				
			}
		}
		
	}
}
