package com.revature.service;

import java.util.List;
import java.util.UUID;

import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeHibernate;
import com.revature.models.Employee;

public class EmployeeService {

	EmployeeDao ed = new EmployeeHibernate();
	
	public List<Employee> getEmployees() {
		List<Employee> emps = ed.getEmployees();
		return emps;
	}
	
	public Employee getEmployeeById(UUID id) {
		Employee emp = ed.getById(id);
		
		return emp;
		
	}
	
	public boolean updateEmployee(Employee e) {
		boolean update = ed.updateEmployee(e);
		
		return update;
	}
}

