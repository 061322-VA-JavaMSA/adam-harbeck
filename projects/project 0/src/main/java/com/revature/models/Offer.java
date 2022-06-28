package com.revature.models;

import java.util.Objects;
import java.util.UUID;

public class Offer {

	private UUID id;
	private UUID shopUserId;
	private UUID evId;
	private double offer;
	
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
	public double getOffer() {
		return offer;
	}
	public void setOffer(double offer) {
		this.offer = offer;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(evId, id, offer, shopUserId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Offer other = (Offer) obj;
		return Objects.equals(evId, other.evId) && Objects.equals(id, other.id)
				&& Double.doubleToLongBits(offer) == Double.doubleToLongBits(other.offer)
				&& Objects.equals(shopUserId, other.shopUserId);
	}
	
	@Override
	public String toString() {
		return "Offer [id=" + id + ", shopUserId=" + shopUserId + ", evId=" + evId + ", offer=" + offer + "]";
	}
	
	
	
}
