package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import com.revature.models.EV;
import com.revature.util.ConnectionUtil;

public class EVPostgresController implements EVDao{

	@Override
	public EV createNewEV(EV newEv) {

		
		return null;
	}

	@Override
	public List<EV> getAllEVs() {

		
		return null;
	}

	@Override
	public List<EV> getAvailableEVs() {

		
		return null;
	}

	@Override
	public List<EV> getOwnedEVs() {

		
		return null;
	}

	@Override
	public EV getEVByModel(String modelName) {

		
		return null;
	}

	@Override
	public EV getEVById(EV evById) {

		
		return null;
	}

	@Override
	public boolean updateEV(EV evToUpdate) {

		
		return false;
	}

	@Override
	public boolean deleteEV(UUID uuid) {
		String sql = "delete from evs where id = ?;";
		int rowChanged = -1;
		
		try(Connection connect = ConnectionUtil.getConnection()){
			PreparedStatement preppedStatement = connect.prepareStatement(sql);
			
			preppedStatement.setObject(1, uuid);
			
			rowChanged = preppedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if(rowChanged < 1) {
			return false;
		}
		
		return true;
	}

}
