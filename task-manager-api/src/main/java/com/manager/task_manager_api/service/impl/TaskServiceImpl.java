package com.manager.task_manager_api.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.server.ResponseStatusException;

import com.manager.task_manager_api.dto.TaskDTO;
import com.manager.task_manager_api.model.Task;
import com.manager.task_manager_api.repository.TaskRepository;
import com.manager.task_manager_api.service.TaskService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TaskServiceImpl implements TaskService {
	
	private final TaskRepository taskRepository;
	
	public TaskServiceImpl(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	@Override
	public TaskDTO createTask(Task task) {
		if (ObjectUtils.isEmpty(task)) {
			log.error("Task cannot be null or empty");
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Task cannot be null or empty at"
					 + LocalDateTime.now());
		}
		log.info("Creating task: {}", task.getTitle());
		Task taskToSave = taskRepository.save(task);
		return new TaskDTO(taskToSave);
	}

	@Override
	public TaskDTO updateTask(Long id, Task task) {
		if (ObjectUtils.isEmpty(id) || ObjectUtils.isEmpty(task)) {
			log.error("Task ID and task details cannot be null or empty");
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Task ID and task details cannot be null or empty at "
					+ LocalDateTime.now());
		}
		
		Task existingTask = taskRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found with ID: " + id 
						+ " at " + LocalDateTime.now()));
		BeanUtils.copyProperties(task, existingTask, "id", "createdAt");
		
		return new TaskDTO(existingTask); 
	}

	@Override
	public TaskDTO getTaskById(Long id) {
		if (ObjectUtils.isEmpty(id)) {
			log.error("Task ID cannot be null or empty");
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Task ID cannot be null or empty at " + LocalDateTime.now());
		}
		Task task = taskRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found with ID: " + id 
						+ " at " + LocalDateTime.now()));
		return new TaskDTO(task);
	}

	@Override
	public void deleteTask(Long id) {
		if (ObjectUtils.isEmpty(id)) {
			log.error("Task ID cannot be null or empty");
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Task ID cannot be null or empty at " + LocalDateTime.now());
		}
		taskRepository.deleteById(id);
		log.info("Task with ID {} deleted successfully", id);
	}

	@Override
	public List<TaskDTO> getAllTasks() {
		log.info("Fetching all tasks");
		if (taskRepository.findAll().isEmpty()) {
			log.warn("No tasks found");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No tasks found at " + LocalDateTime.now());
		}
		List<Task> tasks = taskRepository.findAll();
		return tasks.stream()
				.map(TaskDTO::new)
				.toList();
	}

}
