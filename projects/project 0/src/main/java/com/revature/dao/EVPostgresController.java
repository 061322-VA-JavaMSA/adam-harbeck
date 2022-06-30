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
import com.revature.util.ConnectionUtil;
import com.revature.util.EVScreens;

public class EVPostgresController implements EVDao{
	
	private static Logger log = LogManager.getLogger(EVPostgresController.class);

	@Override
	public EV createNewEV(EV newEv) {
		String sql = "insert into evs (id, brand, model, v_range, v_type_id, shop_user_id, final_price) values (?, ?, ?, ?, ?, ?, ?) returning id";
		
		try (Connection connect = ConnectionUtil.getConnection()) {
			PreparedStatement ps = connect.prepareStatement(sql);
			
			ps.setObject(1, newEv.getId());
			ps.setString(2,  newEv.getBrand());
			ps.setString(3, newEv.getModel());
			ps.setInt(4, newEv.getRange());
			ps.setInt(5,  newEv.getVehicleTypeId());
			ps.setObject(6, newEv.getShopUserId());
			ps.setDouble(7, newEv.getFinalPrice());
			
			ResultSet results = ps.executeQuery();
			
			if(results.next()) {
				log.info("A new user has been created");
			}
			
		} catch (SQLException | IOException e) {
			e.printStackTrace();
			System.out.println("EV couldn't be added.");
			log.error("Exception has been thrown in createNewEV: " + e.fillInStackTrace());
			try {
				EVScreens.createEVScreen();
			} catch (IOException e1) {

				e1.printStackTrace();
			}
		}
		return newEv;
	}

	@Override
	public List<EV> getAllEVs() {
		String sql = "select * from evs";
		List<EV> elecVehicles = new ArrayList<>();
		
		try(Connection connect = ConnectionUtil.getConnection()) {
			Statement s = connect.createStatement();
			
			ResultSet results = s.executeQuery(sql);
			
			while(results.next()) {
				EV newEv = new EV();
				newEv.setId(results.getObject("id", java.util.UUID.class));
				newEv.setBrand(results.getString("brand"));
				newEv.setModel(results.getString("model"));
				newEv.setRange(results.getInt("v_range"));
				newEv.setVehicleTypeId(results.getInt("v_type_id"));
				newEv.setShopUserId(results.getObject("shop_user_id", java.util.UUID.class));
				newEv.setFinalPrice(results.getDouble("final_price"));
				
				elecVehicles.add(newEv);
			}
			
		} catch (SQLException | IOException e) {
			log.error("Exception was thrown in getAllEVs: " + e.fillInStackTrace());
			e.printStackTrace();
		} 
		
		return elecVehicles;
	}

	@Override
	public List<EV> getAvailableEVs() {
		String sql = "select * from evs where shop_user_id is null";
		List<EV> availableEvs = new ArrayList<>();
		
		try(Connection connect = ConnectionUtil.getConnection()) {
			Statement s = connect.createStatement();
			
			ResultSet results = s.executeQuery(sql);
			
			while(results.next()) {
				EV newEv = new EV();
				newEv.setId(results.getObject("id", java.util.UUID.class));
				newEv.setBrand(results.getString("brand"));
				newEv.setModel(results.getString("model"));
				newEv.setRange(results.getInt("v_range"));
				newEv.setVehicleTypeId(results.getInt("v_type_id"));
				newEv.setShopUserId(results.getObject("shop_user_id", java.util.UUID.class));
				newEv.setFinalPrice(results.getDouble("final_price"));
				
				availableEvs.add(newEv);
			}
			
		} catch (SQLException | IOException e) {
			log.error("Exception was thrown in getAvailableEvs: " + e.fillInStackTrace());
			e.printStackTrace();
		} 
		
		return availableEvs;
	}

	@Override
	public List<EV> getOwnedEVs(UUID uuid) {
		String sql = "select * from evs where shop_user_id = ?;" ;
		List<EV> ownedEvs = new ArrayList<>();
		
		try(Connection connect = ConnectionUtil.getConnection()) {
			PreparedStatement s = connect.prepareStatement(sql);
			s.setObject(1, uuid);
			
			ResultSet results = s.executeQuery();
			
			while(results.next()) {
				EV newEv = new EV();
				newEv.setId(results.getObject("id", java.util.UUID.class));
				newEv.setBrand(results.getString("brand"));
				newEv.setModel(results.getString("model"));
				newEv.setRange(results.getInt("v_range"));
				newEv.setVehicleTypeId(results.getInt("v_type_id"));
				newEv.setShopUserId(results.getObject("shop_user_id", java.util.UUID.class));
				newEv.setFinalPrice(results.getDouble("final_price"));
				
				ownedEvs.add(newEv);
			}
			
		} catch (SQLException | IOException e) {
			log.error("Exception was thrown in getOwnedEvs: " + e.fillInStackTrace());
			e.printStackTrace();
		} 
		
		return ownedEvs;
	}

	@Override
	public EV getEVByModel(String modelName) {
		String sql = "select * from evs where model = ?;";
		EV retrievedEV = new EV();
		
		try (Connection connect = ConnectionUtil.getConnection()) {
			PreparedStatement ps = connect.prepareStatement(sql);
			
			ps.setObject(1, modelName);
			
			ResultSet results = ps.executeQuery();
			
			while(results.next()) {
				retrievedEV.setId(results.getObject("id", java.util.UUID.class));
				retrievedEV.setBrand(results.getString("brand"));
				retrievedEV.setModel(results.getString("model"));
				retrievedEV.setRange(results.getInt("v_range"));
				retrievedEV.setVehicleTypeId(results.getInt("v_type_id"));
				retrievedEV.setShopUserId(results.getObject("shop_user_id", java.util.UUID.class));
				retrievedEV.setFinalPrice(results.getDouble("final_price"));
			}
			
			
		} catch (SQLException | IOException e) {
			log.error("Exception was thrown in getEVByModel: " + e.fillInStackTrace());
			e.printStackTrace();
		}
		
		return retrievedEV;
		
	}

	@Override
	public EV getEVById(UUID evById) {
		String sql = "select * from evs where id = ?;";
		EV retrievedEV = new EV();
		
		try (Connection connect = ConnectionUtil.getConnection()) {
			PreparedStatement ps = connect.prepareStatement(sql);
			
			ps.setObject(1, evById);
			
			ResultSet results = ps.executeQuery();
			
			while(results.next()) {
				retrievedEV.setId(evById);
				retrievedEV.setBrand(results.getString("brand"));
				retrievedEV.setModel(results.getString("model"));
				retrievedEV.setRange(results.getInt("v_range"));
				retrievedEV.setVehicleTypeId(results.getInt("v_type_id"));
				retrievedEV.setShopUserId(results.getObject("shop_user_id", java.util.UUID.class));
				retrievedEV.setFinalPrice(results.getDouble("final_price"));
			}
			
			
		} catch (SQLException | IOException e) {
			log.error("Exception was thrown in getEVById: " + e.fillInStackTrace());
			e.printStackTrace();
		}
		
		return retrievedEV;
	}

	@Override
	public boolean updateEV(EV evToUpdate) {
		String sql = "update evs set brand = ?, model = ?, v_range = ?, v_type_id = ?, shop_user_id = ?, final_price =? where id = ?;";
		int rowsUpdated = -1;
		
		try (Connection connect = ConnectionUtil.getConnection()) {
			PreparedStatement ps = connect.prepareStatement(sql);
			
			ps.setString(1, evToUpdate.getBrand());
			ps.setString(2, evToUpdate.getModel());
			ps.setInt(3, evToUpdate.getRange());
			ps.setInt(4, evToUpdate.getVehicleTypeId());
			ps.setObject(5, evToUpdate.getShopUserId());
			ps.setDouble(6, evToUpdate.getFinalPrice());
			ps.setObject(7, evToUpdate.getId());
			rowsUpdated = ps.executeUpdate();
			
		} catch (SQLException | IOException e) {
			log.error("Exception was thrown in updateEV: " + e.fillInStackTrace());
			e.printStackTrace();
		}
		
		if(rowsUpdated < 1) {
			return false;
		}
		log.info("EV with id: " + evToUpdate.getId() + " was updated.");
		return true;
	}

	@Override
	public boolean deleteEV(UUID uuid) {
		String sql = "delete from evs where id = ?;";
		int rowChanged = -1;
		
		try(Connection connect = ConnectionUtil.getConnection()){
			PreparedStatement preppedStatement = connect.prepareStatement(sql);
			
			preppedStatement.setObject(1, uuid);
			
			rowChanged = preppedStatement.executeUpdate();
			
		} catch (SQLException | IOException e) {
			log.error("Exception was thrown in deleteEV: " + e.fillInStackTrace());
			e.printStackTrace();
		} 

		if(rowChanged < 1) {
			return false;
		}
		log.info("EV with id: " + uuid + " has been deleted.");
		return true;
	}

}
