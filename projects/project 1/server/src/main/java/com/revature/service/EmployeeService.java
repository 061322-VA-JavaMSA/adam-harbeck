package com.revature.service;

import java.util.List;

import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeePostgres;
import com.revature.models.Employee;

public class EmployeeService {

	EmployeeDao ed = new EmployeePostgres();
	
	public List<Employee> getEmployees() {
		List<Employee> emps = ed.getEmployees();
		return emps;
	}
}
