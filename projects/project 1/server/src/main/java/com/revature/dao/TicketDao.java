package com.revature.dao;

import java.util.List;
import java.util.UUID;

import com.revature.models.Employee;
import com.revature.models.Ticket;

public interface TicketDao {

	// Get pending
	// Get resolved
	// Get pending by username/id
	// Get resolved by username/id
	// Get ticket by ID -- I'll already have a list and can pull the id from that.
	// Update
	List<Ticket> getPending();
	List<Ticket> getResolved();
	List<Ticket> getEmployeePending(UUID id);
	List<Ticket> getEmployeeResolved(UUID id);
	boolean updateTicket(Ticket t);
	Ticket createTicket(Ticket t);
	
}
