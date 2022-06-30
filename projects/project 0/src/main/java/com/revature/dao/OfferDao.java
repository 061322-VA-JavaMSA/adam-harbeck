package com.revature.dao;

import java.util.List;
import java.util.UUID;

import com.revature.models.Offer;
import com.revature.models.PendingOffers;

public interface OfferDao {

	// Get all offers - sorted by user ID
	// Delete offers
	// Create a new offer
	List<PendingOffers> getAllOffers();
	List<Offer> getOffersByEvId(UUID id);
	Offer getOfferById(UUID id);
	Offer newOffer(Offer o);

	boolean rejectOffer(UUID id);
	boolean deleteOffers(List<Offer> o);
	Offer highestOffer(UUID id);
}
