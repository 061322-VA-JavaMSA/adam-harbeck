package com.revature.models;

import java.util.Objects;
import java.util.UUID;

public class Payment {

	private UUID id;
	private UUID shopUserId;
	private UUID evId;
	private double originalBalance;
	private double payment;
	private double newBalance;
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public UUID getShopUserId() {
		return shopUserId;
	}
	public void setShopUserId(UUID shopUserId) {
		this.shopUserId = shopUserId;
	}
	public UUID getEvId() {
		return evId;
	}
	public void setEvId(UUID evId) {
		this.evId = evId;
	}
	public double getOriginalBalance() {
		return originalBalance;
	}
	public void setOriginalBalance(double originalBalance) {
		this.originalBalance = originalBalance;
	}
	public double getPayment() {
		return payment;
	}
	public void setPayment(double payment) {
		this.payment = payment;
	}
	public double getNewBalance() {
		return newBalance;
	}
	public void setNewBalance(double newBalance) {
		this.newBalance = newBalance;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(evId, id, newBalance, originalBalance, payment, shopUserId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Payment other = (Payment) obj;
		return Objects.equals(evId, other.evId) && Objects.equals(id, other.id)
				&& Double.doubleToLongBits(newBalance) == Double.doubleToLongBits(other.newBalance)
				&& Double.doubleToLongBits(originalBalance) == Double.doubleToLongBits(other.originalBalance)
				&& Double.doubleToLongBits(payment) == Double.doubleToLongBits(other.payment)
				&& Objects.equals(shopUserId, other.shopUserId);
	}
	
	@Override
	public String toString() {
		return "Payment [id=" + id + ", shopUserId=" + shopUserId + ", evId=" + evId + ", originalBalance="
				+ originalBalance + ", payment=" + payment + ", newBalance=" + newBalance + "]";
	}
	
	
}
