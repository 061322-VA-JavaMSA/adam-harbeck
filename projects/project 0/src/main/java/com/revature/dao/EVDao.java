package com.revature.dao;

import java.util.List;
import java.util.UUID;

import com.revature.models.EV;

public interface EVDao {

	/*
	 	EV CRUD
	 	CREATE
	 	- Create a new EV
	 	READ
	 	- Get all EVs
	 	- Get all available EVs
	 	- Get all owned EVs
	 	- Get one EV by model
	 	- Get one EV by id
	 	UPDATE
	 	- update by id
	 	DELETE
	 	- Delete by id
	 
	 */
	
	EV createNewEV(EV newEv);
	List<EV> getAllEVs();
	List<EV> getAvailableEVs();
	List<EV> getOwnedEVs(UUID uuid);
	EV getEVByModel(String modelName);
	EV getEVById(UUID evById);
	boolean updateEV(EV evToUpdate);
	boolean deleteEV(UUID uuid);
}
