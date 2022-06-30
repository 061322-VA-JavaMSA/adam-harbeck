package com.revature.models;

import java.util.Objects;
import java.util.UUID;

public class PendingOffers {

	private UUID offerId;
	private double offer;
	private UUID evId;
	private String brand;
	private String model;
	private UUID shopUserId;
	private String username;
	
	public UUID getOfferId() {
		return offerId;
	}
	public void setOfferId(UUID offerId) {
		this.offerId = offerId;
	}
	public double getOffer() {
		return offer;
	}
	public void setOffer(double offer) {
		this.offer = offer;
	}
	public UUID getEvId() {
		return evId;
	}
	public void setEvId(UUID evId) {
		this.evId = evId;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public UUID getShopUserId() {
		return shopUserId;
	}
	public void setShopUserId(UUID shopUserId) {
		this.shopUserId = shopUserId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(brand, evId, model, offer, offerId, shopUserId, username);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PendingOffers other = (PendingOffers) obj;
		return Objects.equals(brand, other.brand) && Objects.equals(evId, other.evId)
				&& Objects.equals(model, other.model)
				&& Double.doubleToLongBits(offer) == Double.doubleToLongBits(other.offer)
				&& Objects.equals(offerId, other.offerId) && Objects.equals(shopUserId, other.shopUserId)
				&& Objects.equals(username, other.username);
	}
	
	@Override
	public String toString() {
		return "PendingOffers [offerId=" + offerId + ", offer=" + offer + ", evId=" + evId + ", brand=" + brand
				+ ", model=" + model + ", shopUserId=" + shopUserId + ", username=" + username + "]";
	}
	
	
}
