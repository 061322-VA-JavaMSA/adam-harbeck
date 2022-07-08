package com.revature.dao;

import java.io.IOException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.revature.models.Employee;
import com.revature.models.Ticket;
import com.revature.util.HibernateUtil;

public class TicketHibernate implements TicketDao{

	@Override
	public List<Ticket> getPending() {
		List<Ticket> tickets = null;
		try(Session s = HibernateUtil.getSessionFactory().openSession()) {
			tickets = s.createQuery("from Ticket where status = 'PENDING'", Ticket.class).list();
			
		} catch (HibernateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tickets;
	}

	@Override
	public List<Ticket> getResolved() {
		List<Ticket> tickets = null;
		try(Session s = HibernateUtil.getSessionFactory().openSession()) {
			tickets = s.createQuery("from Ticket where status = 'APPROVED'", Ticket.class).list();
			
		} catch (HibernateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tickets;
	}

	@Override
	public List<Ticket> getEmployeePending(Employee id) {
		List<Ticket> tickets = null;
		
		return null;
	}

	@Override
	public List<Ticket> getEmployeeResolved(Employee id) {
		List<Ticket> tickets = null;
		
		return null;
	}

	@Override
	public boolean updateTicket(Ticket id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Ticket createTicket(Ticket t) {
		// TODO Auto-generated method stub
		return null;
	}

}
