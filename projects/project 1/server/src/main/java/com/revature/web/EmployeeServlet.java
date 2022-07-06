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
import com.revature.service.EmployeeService;

public class EmployeeServlet extends HttpServlet{

	EmployeeService es = new EmployeeService();
	ObjectMapper om = new ObjectMapper();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.addHeader("Content-Type", "application/json");
		
		List<Employee> emps = es.getEmployees();
		System.out.println(emps);
		List<EmployeeDto> empsDTO = new ArrayList<>();
		
		emps.forEach(e -> empsDTO.add(new EmployeeDto(e)));
		
		PrintWriter pw = res.getWriter();
		pw.write(om.writeValueAsString(empsDTO));
		
		pw.close();
	}
}
