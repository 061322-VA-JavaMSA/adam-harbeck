package com.revature.dao;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
			tickets = s.createQuery("from Ticket where status = 'APPROVED' or status = 'REJECTED'", Ticket.class).list();
			
		} catch (HibernateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tickets;
	}

	@Override
	public List<Ticket> getEmployeePending(UUID id) {
		List<Ticket> tickets = null;
		System.out.println(id);
		try(Session s = HibernateUtil.getSessionFactory().openSession()) {
			tickets = s.createQuery("from Ticket where status = 'PENDING' and author = :id", Ticket.class).setParameter("id", id).list();
			
		} catch (HibernateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tickets;
	}

	@Override
	public List<Ticket> getEmployeeResolved(UUID id) {
		List<Ticket> tickets = null;
		try(Session s = HibernateUtil.getSessionFactory().openSession()) {
			tickets = s.createQuery("from Ticket where status = 'APPROVED' and author = :id or status = 'REJECTED' and author = :id", Ticket.class).setParameter("id", id).list();
			
		} catch (HibernateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tickets;
	}
	
	@Override
	public List<Ticket> getAllEmployeeTickets(UUID id) {
		List<Ticket> tickets = null;
		try(Session s = HibernateUtil.getSessionFactory().openSession()) {
			tickets = s.createQuery("from Ticket where author = :id", Ticket.class).setParameter("id", id).list();
			
		} catch (HibernateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tickets;
	}

	@Override
	public boolean updateTicket(Ticket t) {
		try(Session s = HibernateUtil.getSessionFactory().openSession()) {
			Transaction tx = s.beginTransaction();
			Ticket tick = (Ticket) s.get(Ticket.class, t.getId());
			System.out.println(tick);
//			tick.setId(t.getId());
			tick.setStatus(t.getStatus());
			tick.setApprovedBy(t.getApprovedBy());
			System.out.println(tick);
			s.merge(tick);
			tx.commit();
			return true;
		} catch (HibernateException | IOException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			return false;
		}
		

	}

	@Override
	public Ticket createTicket(Ticket t) {
		// TODO Auto-generated method stub
		return null;
	}



}
