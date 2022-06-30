package com.revature.services;

import java.util.List;
import java.util.UUID;

import com.revature.dao.EVDao;
import com.revature.dao.EVPostgresController;
import com.revature.dao.OfferDao;
import com.revature.dao.OfferPostgresController;
import com.revature.models.EV;
import com.revature.models.Offer;
import com.revature.models.PendingOffers;
import com.revature.models.User;

public class OfferService {

	OfferDao oDao = new OfferPostgresController();
	EVDao eDao = new EVPostgresController();
	
	public List<PendingOffers> getOffers() {
		
		return oDao.getAllOffers();
	}
	
	public void createOffer(EV elec, User u, double offer) {
		Offer o = new Offer();
		o.setId(UUID.randomUUID());
		o.setShopUserId(u.getId());
		o.setEvId(elec.getId());
		o.setOffer(offer);
		
		oDao.newOffer(o);

	}
	
	public void acceptOffer(UUID id) {
		// Update the EV with the final price from the OFFER, remaining balance from the OFFER, and the shop_user_id from the OFFER
		Offer o = oDao.getOfferById(id);
		EV elec = eDao.getEVById(o.getEvId());
		// now I need to update the EV 
		elec.setFinalPrice(o.getOffer());
		elec.setRemainingBalance(o.getOffer());
		elec.setShopUserId(o.getShopUserId());
		eDao.updateEV(elec);
		// Reject the rest of the offers
		List<Offer> offers = oDao.getOffersByEvId(elec.getId());
		boolean deleted = rejectOffers(offers);
		if(deleted) {
			System.out.println("Offer of " + elec.getFinalPrice() + " accepted for the " + elec.getBrand() + " " + elec.getModel());
		}
	}
	
	public void rejectOffer(UUID id) {
		boolean isRejected = oDao.rejectOffer(id);
		if(!isRejected) {
			System.out.println("Offer wasn't rejected.");
		}
	}
	public boolean rejectOffers(List<Offer> offers) {
		boolean deleted = oDao.deleteOffers(offers);
		return deleted;
	}
	
	public Offer getHighestOffer(UUID id) {
		Offer o = oDao.highestOffer(id);
		
		return o;
	}
}
