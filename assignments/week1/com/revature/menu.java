package com.revature;

import java.util.Scanner;

public class menu {
	static Scanner scan = new Scanner(System.in);
	static boolean proceed = true;
	
	public static void main(String[] args) {
	
		/* 
		 The point is to create a repeatable menu that gives the user options and executes
		 based on the choice.
		 Need a variable to keep the app running while the user still wishes to use the application
		*/
		while(proceed) {
			showMenu();
		}
		
		scan.close();
	}
	
	
	
	public static void showMenu() {
		/*
		  This needs to show options and allows the user to choose an option. 
		  
		*/
		System.out.println("Welcome to the Menu! \nPlease choose an option below");
		System.out.println("1: Get a random number \n2: Reverse a word \n3: Get the area of a square \n4: Exit Program");
		
		String num = scan.nextLine();
		
		switch(num) {
		case "1":
			// Logic to get a random number;
			randomNum();
			break;
		case "2":
			// Logic to reverse a word
			break;
		case "3": 
			// Logic to get area of a square
			break;
		case "4":
			// logic to exit
			proceed = false
			System.out.println("Thanks for using the application!");
			break;
		default:
			showMenu();
		}
	}
	public static randomNum() {
		double randNum = Math.random() + 1;
		System.out.println(randNum);
		showMenu();
	}
	public static stringReverse() {
		System.out.println("What is the word you want to reverse?");
		StringBuilder input = scan.nextLine();
		System.out.println("Your word reversed is " + input.reverse(input));
		System.out.println("Press any key to continue");
		String btn = scan.nextLine();
		showMenu();
		
	}

}
