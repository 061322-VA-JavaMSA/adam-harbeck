package com.revature;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.Date;
import java.util.UUID;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.exceptions.IdNotFoundException;
import com.revature.exceptions.UpdateTicketException;
import com.revature.models.ReimbType;
import com.revature.models.Status;
import com.revature.models.Ticket;
import com.revature.service.TicketService;

public class TicketServiceTest {

	// Employee id
	private static UUID empId;
	private static Ticket t;
	private static TicketService ts;
	private static ReimbType r;
	private static  Date d;
	
	
	
	@BeforeAll
	public static void setup() {
		ts = new TicketService();
		empId = UUID.fromString("90bc6cfc-758a-4017-8906-d5406606w5r6");
		t.setId(UUID.fromString("e1062a98-51b9-41f0-b44c-12443d12043b"));
		t.setAmount(23.5);
		t.setAuthor(UUID.fromString("72df26b7-c4bb-48f3-8cb9-4ffcbb112f9d"));
		t.setDescription("Travel reimbursement from going to other office.");
		t.setSubmitted(Date.valueOf("2022-07-13"));
		t.setType(ReimbType.TRAVEL);
	}
	
	@AfterAll
	public static void tearDown() {
		
	}
	
	@BeforeEach
	public void before() {
		
	}
	
	@Test
	public void empPending() {
		assertThrows(IdNotFoundException.class, () -> ts.getEmployeePending(empId));
	}
	
	@Test
	public void empResolved() {
		assertThrows(IdNotFoundException.class, () -> ts.getEmployeeResolved(empId));
	}
	
	@Test
	public void empUpdate() {
		assertThrows(UpdateTicketException.class, () -> ts.updateTicket(t));
	}
}
