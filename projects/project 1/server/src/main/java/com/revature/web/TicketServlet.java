package com.revature.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Ticket;
import com.revature.service.TicketService;
import com.revature.util.Cors;

public class TicketServlet extends HttpServlet{
	
	TicketService ts = new TicketService();
	ObjectMapper om = new ObjectMapper();
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Cors.addCorsHeader(req.getRequestURI(), res);
		res.addHeader("Content-Type", "application/json");

		List<Ticket> tickets = ts.getPending();
		
		PrintWriter pw = res.getWriter();
		pw.write(om.writeValueAsString(tickets));
		res.setStatus(200);
		pw.close();
	}
}
