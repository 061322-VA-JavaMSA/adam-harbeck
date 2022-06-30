package com.revature.models;

import java.util.Objects;
import java.util.UUID;

public class EV {

	private UUID id;
	private String brand;
	private String model;
	private int range;
	private int vehicleTypeId;
	private UUID shopUserId;
	private double finalPrice;
	private double remainingBalance;
	
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
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
	public int getRange() {
		return range;
	}
	public void setRange(int range) {
		this.range = range;
	}
	public int getVehicleTypeId() {
		return vehicleTypeId;
	}
	public void setVehicleTypeId(int vehicleTypeId) {
		this.vehicleTypeId = vehicleTypeId;
	}
	public UUID getShopUserId() {
		return shopUserId;
	}
	public void setShopUserId(UUID shopUserId) {
		this.shopUserId = shopUserId;
	}
	public double getFinalPrice() {
		return finalPrice;
	}
	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
	}
	public double getRemainingBalance() {
		return remainingBalance;
	}
	public void setRemainingBalance(double remainingBalance) {
		this.remainingBalance = remainingBalance;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(brand, finalPrice, id, model, range, remainingBalance, shopUserId, vehicleTypeId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EV other = (EV) obj;
		return Objects.equals(brand, other.brand)
				&& Double.doubleToLongBits(finalPrice) == Double.doubleToLongBits(other.finalPrice)
				&& Objects.equals(id, other.id) && Objects.equals(model, other.model) && range == other.range
				&& Double.doubleToLongBits(remainingBalance) == Double.doubleToLongBits(other.remainingBalance)
				&& Objects.equals(shopUserId, other.shopUserId) && vehicleTypeId == other.vehicleTypeId;
	}
	
	@Override
	public String toString() {
		return "EV [id=" + id + ", brand=" + brand + ", model=" + model + ", range=" + range + ", vehicleTypeId="
				+ vehicleTypeId + ", shopUserId=" + shopUserId + ", finalPrice=" + finalPrice + ", remainingBalance="
				+ remainingBalance + "]";
	}
	
	
	
	

	
	
}
