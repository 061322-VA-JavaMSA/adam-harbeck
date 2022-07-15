package com.revature.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dto.EmployeeDto;
import com.revature.exceptions.UpdateEmployeeException;
import com.revature.models.Employee;
import com.revature.service.EmployeeService;
import com.revature.util.Cors;

public class EmployeeServlet extends HttpServlet{

	EmployeeService es = new EmployeeService();
	ObjectMapper om = new ObjectMapper();
	private static Logger log = LogManager.getLogger(EmployeeServlet.class);
	
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
		log.info("Retrieved all employees.");
		pw.close();
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Cors.addCorsHeader(req.getRequestURI(), res);
		
		Employee e = om.readValue(req.getInputStream(), Employee.class);
		boolean updated;
		try {
			updated = es.updateEmployee(e);
			res.setStatus(202);
			log.info("Employee with ID: " + e.getId() + " has been updated.");
		} catch (UpdateEmployeeException e1) {
			res.setStatus(304);
			log.error("Employee with ID: " + e.getId() + " was not updated.");
			e1.printStackTrace();
		}
		
	}
	
	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		Cors.addCorsHeader(req.getRequestURI(),res);
		super.doOptions(req, res);
	}
}
