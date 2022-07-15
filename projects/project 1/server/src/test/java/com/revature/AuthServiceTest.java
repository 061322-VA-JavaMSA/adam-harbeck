package com.revature;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.exceptions.LoginException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.service.AuthService;

public class AuthServiceTest {

	private static String invUsername;
	private static String invPassword;
	private static String vUsername;
	private static String vPassword;
	private static AuthService authServ;
	
	@BeforeAll
	public static void setup() {
		authServ = new AuthService();
		invUsername = "addh";
		invPassword = "password";
		vUsername = "adharb";
		vPassword = "p4$$w0rd";
	}
	
	@AfterAll
	public static void tearDown() {
		
	}
	
	@BeforeEach
	public void before() {
		
	}
	
	@Test
	public void invalid() {
		assertThrows(UserNotFoundException.class, () -> authServ.login(invUsername, invPassword));
	}
	@Test
	public void invalidPassword() {
		assertThrows(LoginException.class, () -> authServ.login(vUsername, invPassword));
	}
	@Test
	public void valid() {
		assertThrows(UserNotFoundException.class, () -> authServ.login(vUsername, vPassword));
	}
	
}
