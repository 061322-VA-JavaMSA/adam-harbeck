package com.revature.web;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.exceptions.IdNotFoundException;
import com.revature.exceptions.UpdateTicketException;
import com.revature.models.Ticket;
import com.revature.service.TicketService;
import com.revature.util.Cors;

public class TicketServlet extends HttpServlet{
	
	TicketService ts = new TicketService();
	ObjectMapper om = new ObjectMapper();
	
	private static Logger log = LogManager.getLogger(TicketServlet.class);
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Cors.addCorsHeader(req.getRequestURI(), res);
		res.addHeader("Content-Type", "application/json");

		String path = req.getPathInfo();

		if(path.equals("/pending")) {
			List<Ticket> tickets = ts.getPending();
			
			PrintWriter pw = res.getWriter();
			pw.write(om.writeValueAsString(tickets));
			res.setStatus(200);
			log.info("Retrieved all pending tickets.");
			pw.close();
			
		} else if(path.equals("/resolved")) {
			List<Ticket> tickets = ts.getResolved();
			
			PrintWriter pw = res.getWriter();
			pw.write(om.writeValueAsString(tickets));
			res.setStatus(200);
			log.info("Retrieved all resolved tickets.");
			pw.close();
			
		} 

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Cors.addCorsHeader(req.getRequestURI(), res);
		res.addHeader("Content-Type", "application/json");
		
		String path = req.getPathInfo();
		if(path == null) {
			UUID id = UUID.randomUUID();
			Ticket t = om.readValue(req.getInputStream(), Ticket.class);
			t.setId(id);
			if(t.getAmount() == 0) {
				res.setStatus(400);
				log.error("Unable to create a new ticket.");
			} else {
				Ticket tk = ts.createTicket(t);
				
				PrintWriter pw = res.getWriter();
				pw.write(om.writeValueAsString(tk));
				res.setStatus(201);
				log.info("New ticket has been created with ID: " + id + ".");
				pw.close();
			}

			
		} else {
			if(path.equals("/emp&tickets=pending")) {

				UUID id = UUID.fromString(req.getParameter("id"));
				
				List<Ticket> tickets = null;
				try {
					tickets = ts.getEmployeePending(id);
					PrintWriter pw = res.getWriter();
					pw.write(om.writeValueAsString(tickets));
					res.setStatus(200);
					log.info("Pending tickets for " + id + " have been retrieved.");
					pw.close();
				} catch (IdNotFoundException e) {
					log.error("Id was not found.");
					res.setStatus(400);
					e.printStackTrace();
				}

			} else if(path.equals("/emp&tickets=resolved")) {
				UUID id = UUID.fromString(req.getParameter("id"));
				System.out.println("Hit");
				List<Ticket> tickets = null;
				try {
					tickets = ts.getEmployeeResolved(id);
					PrintWriter pw = res.getWriter();
					pw.write(om.writeValueAsString(tickets));
					res.setStatus(200);
					log.info("Resolved tickets for " + id + " have been retrieved.");
					pw.close();
				} catch (IdNotFoundException e) {
					log.error("Id was not found.");
					res.setStatus(400);
					e.printStackTrace();
				}
				
			} else if(path.equals("/emp&tickets")) {
				UUID id = UUID.fromString(req.getParameter("id"));
				
				List<Ticket> tickets = null;
				try {
					tickets = ts.getAllEmployeeTickets(id);
					PrintWriter pw = res.getWriter();
					pw.write(om.writeValueAsString(tickets));
					res.setStatus(200);
					log.info("Tickets for " + id + " have been retrieved.");
					pw.close();
				} catch (IdNotFoundException e) {
					log.error("Id was not found.");
					res.setStatus(400);
					e.printStackTrace();
				}
			} 
		}
		
		
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Cors.addCorsHeader(req.getRequestURI(), res);
		
		Ticket t = om.readValue(req.getInputStream(), Ticket.class);
		boolean updated;
		try {
			updated = ts.updateTicket(t);
			res.setStatus(202);
			log.info("Ticket " + t.getId() + " has been updated.");
		} catch (UpdateTicketException e) {
			log.error("Ticket could not be updated.");
			res.setStatus(304);
			e.printStackTrace();
		}
		
	}
	
	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		Cors.addCorsHeader(req.getRequestURI(),res);
		super.doOptions(req, res);
	}
}
