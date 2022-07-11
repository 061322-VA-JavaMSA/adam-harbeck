package com.revature.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dto.EmployeeDto;
import com.revature.models.Employee;
import com.revature.models.Ticket;
import com.revature.service.EmployeeService;
import com.revature.util.Cors;

public class EmployeeServlet extends HttpServlet{

	EmployeeService es = new EmployeeService();
	ObjectMapper om = new ObjectMapper();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Cors.addCorsHeader(req.getRequestURI(), res);
		res.addHeader("Content-Type", "application/json");
		
		List<Employee> emps = es.getEmployees();
		System.out.println(emps);
		List<EmployeeDto> empsDTO = new ArrayList<>();
		emps.forEach(e -> empsDTO.add(new EmployeeDto(e)));
		
		PrintWriter pw = res.getWriter();
		pw.write(om.writeValueAsString(empsDTO));
		
		pw.close();
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Cors.addCorsHeader(req.getRequestURI(), res);
		
		Employee e = om.readValue(req.getInputStream(), Employee.class);
		boolean updated = es.updateEmployee(e);

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
