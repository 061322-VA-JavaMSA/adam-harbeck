package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.models.Offer;
import com.revature.models.PendingOffers;
import com.revature.util.ConnectionUtil;

public class OfferPostgresController implements OfferDao{

	private static Logger log = LogManager.getLogger(OfferPostgresController.class);
	
	
	@Override
	public List<PendingOffers> getAllOffers() {
		String sql = "select o.id, o.offer,e.id as ev_id, e.brand, e.model, s.id as shop_user_id, s.username  from offers as o join evs as e on ev_id = e.id join shop_users s on o.shop_user_id = s.id;";
		List<PendingOffers> allOffers = new ArrayList<>();
		
		try(Connection connect = ConnectionUtil.getConnection()) {
			Statement s = connect.createStatement();
			
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) {
				PendingOffers po = new PendingOffers();
				po.setOfferId(rs.getObject("id", java.util.UUID.class));
				po.setOffer(rs.getDouble("offer"));
				po.setEvId(rs.getObject("ev_id", java.util.UUID.class));
				po.setBrand(rs.getString("brand"));
				po.setModel(rs.getString("model"));
				po.setShopUserId(rs.getObject("shop_user_id", java.util.UUID.class));
				po.setUsername(rs.getString("username"));
				
				allOffers.add(po);
				
			}
			
			
		} catch (SQLException | IOException e) {
			log.error("Exception was thrown in getAllOffers " + e.fillInStackTrace());
			e.printStackTrace();
			System.out.println("There are no pending offers currently.");
		}
		
		return allOffers;
	}

	@Override
	public Offer newOffer(Offer o) {
		String sql = "insert into offers (id, shop_user_id, ev_id, offer) values (?, ?, ?, ?) returning id;";
		
		try (Connection connect = ConnectionUtil.getConnection()) {
			PreparedStatement ps = connect.prepareStatement(sql);
			
			ps.setObject(1, o.getId());
			ps.setObject(2, o.getShopUserId());
			ps.setObject(3, o.getEvId());
			ps.setDouble(4, o.getOffer());
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				System.out.println("New offer made");
				log.info("A new offer was made. Id: " + o.getId());
			}

		} catch (SQLException | IOException e) {
			log.error("Excpetion was thrown in newOffer: " + e.fillInStackTrace());
			e.printStackTrace();
			System.out.println("Offer wasn't accepted");
			o.setId(null);
		} 
		
		return o;
	}
	
	@Override
	public List<Offer> getOffersByEvId(UUID id) {
		String sql = "select * from offers where ev_id = ?";
		List<Offer> offers = new ArrayList<>();
		
		try (Connection connect = ConnectionUtil.getConnection()) {
			PreparedStatement ps = connect.prepareStatement(sql);
			
			ps.setObject(1, id);

			
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				Offer o = new Offer ();
				o.setId(rs.getObject("id", java.util.UUID.class));
				o.setShopUserId(rs.getObject("shop_user_id", java.util.UUID.class));
				o.setEvId(rs.getObject("ev_id", java.util.UUID.class));
				o.setOffer(rs.getDouble("offer"));
				
				offers.add(o);
				
			}
			
		} catch (SQLException | IOException e) {
			log.error("Excpetion was thrown in getOffersByEvId: " + e.fillInStackTrace());
			e.printStackTrace();
		} 
		
		return offers;
	}
	
	@Override
	public Offer getOfferById(UUID id) {
		String sql = "select * from offers where id = ?";
		Offer o = new Offer();
		try (Connection connect = ConnectionUtil.getConnection()) {
			PreparedStatement ps = connect.prepareStatement(sql);
			
			ps.setObject(1, id);
			
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				o.setId(rs.getObject("id", java.util.UUID.class));
				o.setShopUserId(rs.getObject("shop_user_id", java.util.UUID.class));
				o.setEvId(rs.getObject("ev_id", java.util.UUID.class));
				o.setOffer(rs.getDouble("offer"));
			}
			
		} catch (SQLException | IOException e) {
			log.error("Excpetion was thrown in getOfferById: " + e.fillInStackTrace());
			e.printStackTrace();
		} 
		
		return o;
		
	}


	@Override
	public boolean rejectOffer(UUID id) {
		String sql = "delete from offers where id = ?;";
		int rowsChanged = -1;
		
		try(Connection c = ConnectionUtil.getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);
			
			ps.setObject(1, id);
			
			rowsChanged = ps.executeUpdate();
			
		

		} catch (SQLException | IOException e) {
			log.error("Excpetion was thrown in rejectOffer: " + e.fillInStackTrace());
			e.printStackTrace();
		}
		
		if(rowsChanged < 1) {
			return false;
			
		}
		log.info("Offer was rejected with id: " + id);
		return true;
	}

	@Override
	public boolean deleteOffers(List<Offer> offers) {
		String sql = "delete from offers where id = ?;";
		
		for(Offer o : offers) {
			try(Connection c = ConnectionUtil.getConnection()) {
				PreparedStatement ps = c.prepareStatement(sql);
				
				ps.setObject(1, o.getId());
				
				ps.executeUpdate();
				
			} catch (SQLException | IOException e) {
				log.error("Excpetion was thrown in deleteOffers: " + e.fillInStackTrace());
				e.printStackTrace();
			}
		}
		
		log.info("All offers for id: " + offers.get(0).getEvId() + " have been deleted.");
		return true;
	}

	
	@Override
	public Offer highestOffer(UUID id) {
		String sql = "select max(offer) as offer from offers where ev_id = ?; ";
		Offer o = new Offer();
		
		try (Connection c = ConnectionUtil.getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setObject(1, id);			
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				// id, shop_id, ev_id, offer
				o.setId(id);
				o.setOffer(rs.getDouble("offer"));
			}
			
			
		} catch (SQLException | IOException e) {
			log.error("Excpetion was thrown in highestOffer: " + e.fillInStackTrace());
			e.printStackTrace();
			System.out.println("No offers have been made on this EV.");
		}
		
		return o;
	}




}
