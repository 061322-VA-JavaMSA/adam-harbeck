package com.revature.dao;

import java.util.List;
import java.util.UUID;

import com.revature.models.Payment;

public interface PaymentDao {

	List<Payment> getAllPayment();
	Payment getPaymentById(UUID id);
	Payment newPayment(Payment p);
	
}
