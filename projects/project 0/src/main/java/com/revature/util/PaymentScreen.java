package com.revature.util;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.revature.models.EV;
import com.revature.models.Payment;
import com.revature.models.User;
import com.revature.services.PaymentService;

public class PaymentScreen {
	static PaymentService payServ = new PaymentService();
	static Scanner scan = new Scanner(System.in);
	static String breaker = "\n\n\n\n------------------------\n";
	
	public static void viewPayments(User u) {
		List<Payment> payments = payServ.getPayments();
		System.out.println(breaker);
		int i = 1;
		for(Payment p : payments) {
			System.out.println(i + ": Vehicle Id: " + p.getEvId() + " | User id: " + p.getShopUserId() + " | Payment: " + p.getPayment());
			
			i++;
		}
		
		System.out.println("\n\n\nPress enter to return");
		scan.nextLine();
		
		try {
			UserScreens.employeeScreen(u);
		} catch (SQLException | IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public static void remaingPayment(User u) {
		System.out.println(breaker);
		// Get the cars owned AND the ones w/o a 0 balance on them
		List<EV> needsPay = payServ.getUserPayments(u.getId());
		int i = 1;
		for(EV toPay : needsPay) {
			System.out.println(i + ": " + toPay.getBrand() + " " + toPay.getModel() + " " + toPay.getRemainingBalance());
			
		}
		boolean wrongInput = true;
		while(wrongInput) {
			System.out.println("Enter 00 to return.");
			int input = scan.nextInt();
			if(input == 00) {
				wrongInput = false;
			
			} else {
				System.out.println("Only enter 00 to return.");
			}

		}
		
	}
	
}
