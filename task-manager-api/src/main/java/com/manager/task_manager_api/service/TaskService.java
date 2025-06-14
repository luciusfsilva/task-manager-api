package com.manager.task_manager_api.service;

import java.util.List;

import com.manager.task_manager_api.model.Task;

public interface TaskService {
	
	public Task createTask(Task task);
	public Task updateTask(Long id, Task task);
	public Task getTaskById(Long id);
	public void deleteTask(Long id);
	public List<Task> getAllTasks();

}
