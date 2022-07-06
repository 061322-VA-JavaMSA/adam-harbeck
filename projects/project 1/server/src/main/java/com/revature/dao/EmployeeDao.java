package com.revature.dao;

import java.util.List;
import java.util.UUID;

import com.revature.models.Employee;

public interface EmployeeDao {

	// get employee by ID
	// get employee by username
	// get all employees
	List<Employee> getEmployees();
	Employee getByUsername(String username);
	Employee getById(UUID id);
	
}
