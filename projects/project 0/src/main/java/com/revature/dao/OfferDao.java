package com.revature.dao;

import java.util.List;

import com.revature.models.Offer;

public interface OfferDao {

	// Get all offers - sorted by user ID
	// Delete offers
	// Create a new offer
	List<Offer> getAllOffers();
	Offer newOffer(Offer o);
	boolean deleteOffer(List<Offer> o);
}
