package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.revature.models.Employee;
import com.revature.models.Role;
import com.revature.util.ConnectionUtil;

public class EmployeePostgres implements EmployeeDao{

	@Override
	public List<Employee> getEmployees() {
		String sql = "select * from employees";
		List<Employee> emps = new ArrayList<>();
		
		try(Connection c = ConnectionUtil.getConnection()) {
			Statement s = c.createStatement();
			
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) {
				Employee e = new Employee();
				e.setId(rs.getObject("id", java.util.UUID.class));
				e.setUsername(rs.getString("username"));
				e.setPassword(rs.getString("password"));
				e.setFirstName(rs.getString("first_name"));
				e.setLastName(rs.getString("last_name"));
				e.setEmail(rs.getString("email"));
				e.setRole(Role.valueOf(rs.getString("role")));
				
				emps.add(e);
			}
			
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		
		return emps;
	}

	@Override
	public Employee getByUsername(String username) {
		
		return null;
	}

	@Override
	public Employee getById(UUID id) {
		
		return null;
	}

}
