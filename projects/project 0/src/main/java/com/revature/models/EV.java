package com.revature.models;

import java.util.Objects;
import java.util.UUID;

public class EV {

	private UUID id;
	private String brand;
	private String model;
	private int range;
	private int vehicleTypeId;
	private UUID shop_user_id;
	private double finalPrice;
	
	
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
	public UUID getShop_user_id() {
		return shop_user_id;
	}
	public void setShop_user_id(UUID shop_user_id) {
		this.shop_user_id = shop_user_id;
	}
	public double getFinalPrice() {
		return finalPrice;
	}
	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(brand, finalPrice, id, model, range, shop_user_id, vehicleTypeId);
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
				&& Objects.equals(shop_user_id, other.shop_user_id) && vehicleTypeId == other.vehicleTypeId;
	}
	
	@Override
	public String toString() {
		return "EV [id=" + id + ", brand=" + brand + ", model=" + model + ", range=" + range + ", vehicleTypeId="
				+ vehicleTypeId + ", shop_user_id=" + shop_user_id + ", finalPrice=" + finalPrice + "]";
	}
	
	
}
