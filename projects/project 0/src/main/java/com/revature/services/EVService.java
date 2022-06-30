package com.revature.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.UUID;

import com.revature.models.EV;
import com.revature.dao.EVDao;
import com.revature.dao.EVPostgresController;

public class EVService {

	private EVDao eDao = new EVPostgresController();
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	
	public EV createEv(EV newEv) {

		// Check if a model and brand exists already
		EV dbCheck = evByModel(newEv.getModel());
		if(dbCheck.getId() != null) {
			System.out.println("This model already exists");
			return null;
		}
		
		newEv.setId(UUID.randomUUID());
		
		eDao.createNewEV(newEv);
		
		return newEv;
	}
	
	
	public List<EV> availableEVs() {
		return eDao.getAvailableEVs();
		
	} 
	public List<EV> ownedEVs(UUID uuid) {
		return eDao.getOwnedEVs(uuid);
	} 
	
	public EV evByModel(String modelName) {
		EV returnedEv = eDao.getEVByModel(modelName);
		
		return returnedEv;
	}
	
	
	public boolean removeEV(UUID uuid) {
		boolean deleted = eDao.deleteEV(uuid);
		
		return deleted;
	}
}
