package com.revature;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.exceptions.UpdateEmployeeException;
import com.revature.models.Employee;
import com.revature.models.Role;
import com.revature.service.EmployeeService;

public class EmployeeServiceTest {
	
	private static Employee e;
	private static EmployeeService empServ;
	
	@BeforeAll
	public static void setup() {
		empServ = new EmployeeService();
		e.setId(UUID.fromString("7921acac-b183-4676-a843-ccaa5630023a"));
		e.setUsername("adharb");
		e.setPassword("p4$$w0rd");
		e.setFirstName("");
		e.setLastName("Harbeck");
		e.setEmail("adam@example.com");
		e.setRole(Role.MANAGER);
		
	}
	
	@AfterAll
	public static void tearDown() {
		
	}
	
	@BeforeEach
	public void before() {
		
	}
	
	@Test
	public void userUpdated() {
		assertThrows(UpdateEmployeeException.class, () -> empServ.updateEmployee(e));
	}
	
}
