package com.revature.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dto.EmployeeDto;
import com.revature.exceptions.LoginException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Employee;
import com.revature.service.AuthService;
import com.revature.util.Cors;


public class AuthServlet extends HttpServlet{

	private AuthService authServ = new AuthService();
	private ObjectMapper om = new ObjectMapper();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Cors.addCorsHeader(req.getRequestURI(), res);
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		try {
			Employee principal = authServ.login(username, password);
			
			HttpSession session = req.getSession();
			session.setAttribute("EmpId", principal.getId());
			session.setAttribute("EmpRole", principal.getRole());
			
			// To make Chrome work with our cookie
			String cookie = res.getHeader("Set-Cookie") + "; SameSite=None; Secure";
			res.setHeader("Set-Cookie", cookie);;
			
			EmployeeDto principalDTO = new EmployeeDto(principal);
			try(PrintWriter pw = res.getWriter()){
				pw.write(om.writeValueAsString(principalDTO));
				res.setStatus(200);
			}
		} catch (UserNotFoundException | LoginException e) {
			res.sendError(400, "Login unsuccessful.");
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		Cors.addCorsHeader(req.getRequestURI(), res);
		
		HttpSession session = req.getSession();
		
		session.invalidate();
	}
	
	// This fixes the preflight issue
	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		Cors.addCorsHeader(req.getRequestURI(),res);
		super.doOptions(req, res);
	}
}
