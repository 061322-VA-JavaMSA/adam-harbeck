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
			System.out.println("Welcome to the Menu!");
			showMenu();
		}
		
		scan.close();
	}
	
	
	
	public static void showMenu() {
		/*
		  This needs to show options and allows the user to choose an option. 
		*/
		System.out.println("Please choose an option below");
		System.out.println("1: Get a random number \n2: Reverse a word \n3: Exit Program");
		
		String num = scan.nextLine();
		
		switch(num) {
		case "1":
			// Logic to get a random number;
			randomNum();
			break;
		case "2":
			// Logic to reverse a word
			stringReverse();
			break;
		case "3":
			// logic to exit
			proceed = false;
			System.out.println("Thanks for using the application!");
			break;
		default:
			System.out.println("You've input an invalid choice");
			returnToMenu();
		}
	}
	
	public static void randomNum() {
		int randNum = (int)(Math.random() * 100) + 1 ;
		System.out.println(randNum);
		returnToMenu();
	}
	
	public static void stringReverse() {
		System.out.println("What is the word you want to reverse?");
		String input = scan.nextLine();
		StringBuilder sb = new StringBuilder(input);
		System.out.println("Your word reversed is " + sb.reverse());
		returnToMenu();
	}
	
	public static void returnToMenu() {
		System.out.println("Press any key to continue");
		scan.nextLine();
		showMenu();
	}

}
