package com.revature.service;

import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeHibernate;
import com.revature.exceptions.LoginException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Employee;

public class AuthService {

	private EmployeeDao eDao = new EmployeeHibernate();
	
	public Employee login(String username, String password) throws UserNotFoundException, LoginException {
		Employee principal = eDao.getByUsername(username);
		
		if(principal == null) {
			throw new UserNotFoundException();
		}
		
		if(!principal.getPassword().equals(password)){
			throw new LoginException();
		}
		
		return principal;
	}
}
