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

import com.revature.models.EV;
import com.revature.models.Payment;
import com.revature.util.ConnectionUtil;

public class PaymentPostgresController implements PaymentDao{
	private static Logger log = LogManager.getLogger(PaymentPostgresController.class);
	
	@Override
	public List<Payment> getAllPayment() {
		String sql = "select * from payments order by shop_user_id";
		List<Payment> payments = new ArrayList<>();
		
		try(Connection c = ConnectionUtil.getConnection()) {
			Statement s = c.createStatement();
			
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) {
				Payment p = new Payment();
				p.setId(rs.getObject("id", java.util.UUID.class));
				p.setShopUserId(rs.getObject("shop_user_id", java.util.UUID.class));
				p.setEvId(rs.getObject("ev_id", java.util.UUID.class));
				p.setOriginalBalance(rs.getDouble("original_balance"));
				p.setPayment(rs.getDouble("payment"));
				p.setNewBalance(rs.getDouble("new_balance"));
				
				payments.add(p);
			}
			
		} catch (SQLException | IOException e) {
			log.error("Excpetion was thrown in getAllPayments: " + e.fillInStackTrace());
			e.printStackTrace();
		}
		
		return payments;
	}

	@Override // Change to EV list instead. Send back the EVs with a remaining balance.
	public List<EV> getPaymentById(UUID id) {
		
		String sql = "select * from evs where shop_user_id = ? AND remaining_balance > 0;";
		List<EV> remaining = new ArrayList<>();
		
		try(Connection c = ConnectionUtil.getConnection()) {
			PreparedStatement ps = c.prepareStatement(sql);
			
			ps.setObject(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				EV ev = new EV();
				ev.setId(rs.getObject("id", java.util.UUID.class));
				ev.setBrand(rs.getString("brand"));
				ev.setModel(rs.getString("model"));
				ev.setRange(rs.getInt("v_range"));
				ev.setVehicleTypeId(rs.getInt("v_type_id"));
				ev.setShopUserId(rs.getObject("shop_user_id", java.util.UUID.class));
				ev.setFinalPrice(rs.getDouble("final_price"));
				ev.setRemainingBalance(rs.getDouble("remaining_balance"));
				
				remaining.add(ev);
			}
			
		} catch (SQLException | IOException e) {
			log.error("Excpetion was thrown in getPaymentById: " + e.fillInStackTrace());
			e.printStackTrace();
		}
		
		return remaining;
		
	}

	// NOT REQUIRED
	@Override
	public Payment newPayment(Payment p) {
		
		return null;
	}

}
