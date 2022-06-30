package com.revature.services;

import java.util.List;
import java.util.UUID;

import com.revature.models.EV;
import com.revature.models.Payment;
import com.revature.dao.PaymentPostgresController;
import com.revature.dao.PaymentDao;

public class PaymentService {
	
	private PaymentDao pDao = new PaymentPostgresController();

	public List<Payment> getPayments() {
		// Calls on the PaymentPostgresController
		// receives a list of payments back
		List<Payment> payments = pDao.getAllPayment();
		return payments;
	}
	
	public List<EV> getUserPayments(UUID id) {
		return pDao.getPaymentById(id);
		
	}
	
	// NOT REQUIRED 
	public void newPayment(Payment p) {
		
	}
}
