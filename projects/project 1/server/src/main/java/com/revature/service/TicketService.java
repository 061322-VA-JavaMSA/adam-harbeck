package com.revature.service;

import java.util.List;
import java.util.UUID;

import com.revature.dao.TicketDao;
import com.revature.dao.TicketHibernate;
import com.revature.exceptions.IdNotFoundException;
import com.revature.exceptions.UpdateTicketException;
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
	
	public boolean updateTicket(Ticket t) throws UpdateTicketException{
		boolean b;

		b = td.updateTicket(t);
		if(!b) {
			throw new UpdateTicketException();
		}
		return b;

	}
	
	public List<Ticket> getEmployeePending(UUID id) throws IdNotFoundException {
		List<Ticket> tickets = td.getEmployeePending(id);
		if(tickets == null) {
			throw new IdNotFoundException();
		} else {
			return tickets;
		}
		
	}
	
	public List<Ticket> getEmployeeResolved(UUID id) throws IdNotFoundException{
		List<Ticket> tickets = td.getEmployeeResolved(id);
		if(tickets == null) {
			throw new IdNotFoundException();
		} else {
			return tickets;
		}
	}
	
	public List<Ticket> getAllEmployeeTickets(UUID id) throws IdNotFoundException{
		List<Ticket> tickets = td.getAllEmployeeTickets(id);
		
		return tickets;
	}
	
	public Ticket createTicket(Ticket t) {
		Ticket tick;

		tick = td.createTicket(t);
		return tick;

	}
}
