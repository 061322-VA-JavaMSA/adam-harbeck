package com.revature.dao;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.revature.models.Employee;
import com.revature.util.HibernateUtil;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class EmployeeHibernate implements EmployeeDao{

	@Override
	public List<Employee> getEmployees() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee getByUsername(String username) {

		Employee emp = null;
		
		try(Session s = HibernateUtil.getSessionFactory().openSession()) {
//			emp = s.get(Employee.class, username);
			CriteriaBuilder cb = s.getCriteriaBuilder();
			CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
			Root<Employee> root = cq.from(Employee.class);
			
			Predicate p = cb.equal(root.get("username"), username);
			
			cq.select(root).where(p);
			emp = (Employee) s.createQuery(cq).getSingleResult();
			
		} catch (HibernateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return emp;
	}

	@Override
	public Employee getById(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

}
