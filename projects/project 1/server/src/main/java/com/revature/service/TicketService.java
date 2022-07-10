package com.revature.service;

import java.util.List;

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
	

}
