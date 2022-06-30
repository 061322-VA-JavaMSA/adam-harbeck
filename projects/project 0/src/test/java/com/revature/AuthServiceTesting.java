package com.revature;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.exceptions.AuthException;
import com.revature.services.AuthService;

public class AuthServiceTesting {

	private static String invUsername;
	private static String invPassword;
	private static AuthService authServ;
	private static String validUser;
	private static String validPass;
	private static String rightUser;
	private static String wrongPass;
	
	
	@BeforeAll
	public static void setup() {
		authServ = new AuthService();
		invUsername = "notInTheDb";
		invPassword = "totsNot";
		validUser = "ButterQueen";
		validPass = "bettyWhite";
		rightUser = "ButterQueen";
		wrongPass = "MrsWhiteIfYaNasty";
	}
	
	@AfterAll
	public static void tearDown() {
		
	}
	
	@BeforeEach
	public void before() {
		
	}
	
	@Test
	public void invalidUsername() {
		assertThrows(AuthException.class, () -> authServ.login(invUsername, invPassword));
	}
	@Test
	public void validUser() {
		assertThrows(AuthException.class, () -> authServ.login(validUser, validPass));
	}
	@Test
	public void wrongPass() {
		assertThrows(AuthException.class, () -> authServ.login(rightUser, wrongPass));
	}
	
}
