package com.revature.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Status;
import com.revature.models.Ticket;
import com.revature.service.TicketService;
import com.revature.util.Cors;

public class TicketServlet extends HttpServlet{
	
	TicketService ts = new TicketService();
	ObjectMapper om = new ObjectMapper();
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Cors.addCorsHeader(req.getRequestURI(), res);
		res.addHeader("Content-Type", "application/json");

		/*
		  So, this works for the manager. It gets the /pending and /resolved.
		  However, a user is supposed to be able to get their pending and resolved.
		  I could set it to a user such as /tickets/emp=[username]/pending
		 */
		String path = req.getPathInfo();

		if(path.equals("/pending")) {
			List<Ticket> tickets = ts.getPending();
			
			PrintWriter pw = res.getWriter();
			pw.write(om.writeValueAsString(tickets));
			res.setStatus(200);
			pw.close();
			
		} else if(path.equals("/resolved")) {
			List<Ticket> tickets = ts.getResolved();
			
			PrintWriter pw = res.getWriter();
			pw.write(om.writeValueAsString(tickets));
			res.setStatus(200);
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
			System.out.println(t);
			Ticket tk = ts.createTicket(t);
			
			PrintWriter pw = res.getWriter();
			pw.write(om.writeValueAsString(tk));
			res.setStatus(201);
			pw.close();
			
		} else {
			if(path.equals("/emp&tickets=pending")) {


				UUID id = UUID.fromString(req.getParameter("id"));
				System.out.println(id); // Reads null
				List<Ticket> tickets = ts.getEmployeePending(id);
				
				PrintWriter pw = res.getWriter();
				pw.write(om.writeValueAsString(tickets));
				res.setStatus(200);
				pw.close();
				
			} else if(path.equals("/emp&tickets=resolved")) {
				UUID id = UUID.fromString(req.getParameter("id"));
				List<Ticket> tickets = ts.getEmployeeResolved(id);
				
				PrintWriter pw = res.getWriter();
				pw.write(om.writeValueAsString(tickets));
				res.setStatus(200);
				pw.close();
				
			} else if(path.equals("/emp&tickets")) {
				UUID id = UUID.fromString(req.getParameter("id"));
				List<Ticket> tickets = ts.getAllEmployeeTickets(id);
				
				PrintWriter pw = res.getWriter();
				pw.write(om.writeValueAsString(tickets));
				res.setStatus(200);
				pw.close();
			} 
		}
		
		
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Cors.addCorsHeader(req.getRequestURI(), res);
		
		Ticket t = om.readValue(req.getInputStream(), Ticket.class);
		boolean updated = ts.updateTicket(t);

		if(updated) {
			res.setStatus(202);
		} else {
			res.setStatus(304);
		}

		
	}
	
	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		Cors.addCorsHeader(req.getRequestURI(),res);
		super.doOptions(req, res);
	}
}
