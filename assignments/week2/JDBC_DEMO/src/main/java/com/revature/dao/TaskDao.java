package com.revature.dao;

import java.util.List;

import com.revature.models.Task;

public interface TaskDao {

	Task createTask(Task t);
	List<Task> retrieveTasks();
	Task retrieveTaskById(int id);
	List<Task> retrieveTasksByUserId(int id);
	boolean updateTask(Task t);
}
