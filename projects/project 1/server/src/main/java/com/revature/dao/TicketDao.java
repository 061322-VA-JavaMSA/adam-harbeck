package com.revature.dao;

import java.util.List;

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
	List<Ticket> getEmployeePending(Employee id);
	List<Ticket> getEmployeeResolved(Employee id);
	boolean updateTicket(Ticket id);
	Ticket createTicket(Ticket t);
	
}
