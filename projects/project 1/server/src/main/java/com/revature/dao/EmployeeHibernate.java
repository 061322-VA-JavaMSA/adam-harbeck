package com.revature.dao;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Employee;
import com.revature.util.HibernateUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class EmployeeHibernate implements EmployeeDao{
	
	private static Logger log = LogManager.getLogger(EmployeeHibernate.class);
	
	@Override
	public List<Employee> getEmployees() {
		List<Employee> employees = null;
		try(Session s = HibernateUtil.getSessionFactory().openSession()){
			employees = s.createQuery("from Employee where role = 'EMPLOYEE'", Employee.class).list();

		} catch (HibernateException | IOException e) {

			e.printStackTrace();
		}
		
		
		return employees;
	}

	@Override
	public Employee getByUsername(String username) {

		Employee emp = null;
		
		try(Session s = HibernateUtil.getSessionFactory().openSession()) {
			CriteriaBuilder cb = s.getCriteriaBuilder();
			CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
			Root<Employee> root = cq.from(Employee.class);
			
			Predicate p = cb.equal(root.get("username"), username);
			
			cq.select(root).where(p);
			emp = (Employee) s.createQuery(cq).getSingleResult();

			
		} catch (HibernateException | IOException | javax.persistence.NoResultException e) {
			log.error("Username not found.");
			e.printStackTrace();
		}
		
		return emp;
	}

	@Override
	public Employee getById(UUID id){
		Employee emp = null;
		try(Session s = HibernateUtil.getSessionFactory().openSession()) {
			emp = s.get(Employee.class, id);

		} catch (HibernateException | IOException e) {

			e.printStackTrace();
		}
		
		return emp;
	}

	@Override
	public boolean updateEmployee(Employee e){
		try(Session s = HibernateUtil.getSessionFactory().openSession()) {
			Transaction tx = s.beginTransaction();
			Employee emp = (Employee) s.get(Employee.class, e.getId());
			emp.setUsername(e.getUsername());
			emp.setFirstName(e.getFirstName());
			emp.setLastName(e.getLastName());
			emp.setEmail(e.getEmail());
			s.merge(emp);
			tx.commit();

			return true;
			
		} catch (HibernateException | IOException e1) {
			e1.printStackTrace();
			return false;
		}

	}

}
