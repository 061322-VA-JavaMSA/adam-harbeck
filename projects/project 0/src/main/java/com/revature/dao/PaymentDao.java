package com.revature.dao;

import java.util.List;
import java.util.UUID;

import com.revature.models.EV;
import com.revature.models.Payment;

public interface PaymentDao {

	List<Payment> getAllPayment();
	List<EV> getPaymentById(UUID id);
	Payment newPayment(Payment p);
	
}
