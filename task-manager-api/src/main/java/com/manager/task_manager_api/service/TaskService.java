package com.manager.task_manager_api.service;

import java.util.List;

import com.manager.task_manager_api.controller.dto.TaskDTO;
import com.manager.task_manager_api.model.Task;

public interface TaskService {
	
	public TaskDTO createTask(Task task);
	public TaskDTO updateTask(Long id, Task task);
	public TaskDTO getTaskById(Long id);
	public void deleteTask(Long id);
	public List<TaskDTO> getAllTasks();

}
