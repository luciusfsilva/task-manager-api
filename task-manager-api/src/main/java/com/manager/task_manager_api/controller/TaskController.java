package com.manager.task_manager_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manager.task_manager_api.model.Task;
import com.manager.task_manager_api.service.TaskService;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
	
	private final TaskService taskService;
	
	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> createTask(@RequestBody Task task) {
		return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(task));
	}
	
	@PostMapping("/update")
	public ResponseEntity<?> updateTask(@RequestBody Task task) {
		if (task.getId() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Task ID cannot be null for update");
		}
		return ResponseEntity.ok(taskService.updateTask(task.getId(), task));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getTaskById(@PathVariable Long id) {
		if (id == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Task ID cannot be null");
		}
		return ResponseEntity.ok(taskService.getTaskById(id));
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllTasks() {
		return ResponseEntity.ok(taskService.getAllTasks());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteTask(@PathVariable Long id) {
		if (id == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Task ID cannot be null for deletion");
		}
		taskService.deleteTask(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
