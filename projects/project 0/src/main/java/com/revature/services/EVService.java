package com.revature.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.UUID;

import com.revature.models.EV;
import com.revature.util.Validator;
import com.revature.dao.EVDao;
import com.revature.dao.EVPostgresController;

public class EVService {

	private EVDao eDao = new EVPostgresController();
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	
	public EV createEv(EV newEv) {
		// Validate the spelling of the brand
		boolean onlyLetters = Validator.validateName(newEv.getBrand());
		
		if(!onlyLetters) {
			System.out.println("Brand can only be letters.");
			
		}
		
		
		// Check if a model and brand exists already
		
		
		return newEv;
	}
	
	public List<EV> allEVs() {
		return eDao.getAllEVs();
		
	} 
	
	public List<EV> availableEVs() {
		return eDao.getAvailableEVs();
		
	} 
	public List<EV> ownedEVs(UUID uuid) {
		return eDao.getOwnedEVs(uuid);
	} 
	
	public EV evByModel(String modelName) {
		
		return null;
	}
	
	public EV evById(UUID uuid) {
		
		return null;
	}
	
	public boolean evUpdate(EV toUpdate) {
		
		return true;
	}
	
	public boolean removeEV(UUID uuid) {
		
		
		return true;
	}
}
