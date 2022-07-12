package com.revature.service;

import java.util.List;
import java.util.UUID;

import com.revature.dao.TicketDao;
import com.revature.dao.TicketHibernate;
import com.revature.models.Ticket;

public class TicketService {

	private TicketDao td = new TicketHibernate();
	
	public List<Ticket> getPending() {
		List<Ticket> tickets = td.getPending();
		
		return tickets;
	}
	
	public List<Ticket> getResolved() {
		List<Ticket> tickets = td.getResolved();
		
		return tickets;
	}
	
	public boolean updateTicket(Ticket t) {
		boolean b = td.updateTicket(t);
		
		return b;
	}
	
	public List<Ticket> getEmployeePending(UUID id) {
		List<Ticket> tickets = td.getEmployeePending(id);
		
		return tickets;
	}
	
	public List<Ticket> getEmployeeResolved(UUID id) {
		List<Ticket> tickets = td.getEmployeeResolved(id);
		
		return tickets;
	}
	
	public List<Ticket> getAllEmployeeTickets(UUID id) {
		List<Ticket> tickets = td.getAllEmployeeTickets(id);
		
		return tickets;
	}
	
	public Ticket createTicket(Ticket t) {
		Ticket tick = td.createTicket(t);
		
		return tick;
	}
}
