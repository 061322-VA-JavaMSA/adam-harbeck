package com.revature.services;

import java.util.List;

import com.revature.dao.TaskDao;
import com.revature.dao.TaskPostgres;
import com.revature.models.Task;

public class TaskService {

	private TaskDao td = new TaskPostgres();
	
	public List<Task> getTaskByUserId(int id){
		return td.retrieveTasksByUserId(id);
	}
}
