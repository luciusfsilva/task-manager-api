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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
	
	private final TaskService taskService;
	
	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}
	
	@PostMapping("/create")
	@Operation(summary = "Create a new task", description = "This endpoint allows you to create a new task in the task manager.")
	@ApiResponses({
		@ApiResponse(responseCode = "201", description = "Task created successfully"),
		@ApiResponse(responseCode = "400", description = "Bad Request - Invalid task data")})
	public ResponseEntity<?> createTask(@RequestBody Task task) {
		return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(task));
	}
	
	@PostMapping("/update")
	@Operation(summary = "Create a new task", description = "This endpoint allows you to create a new task in the task manager.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Task update successfully"),
		@ApiResponse(responseCode = "400", description = "Bad Request - Invalid task data")})
	public ResponseEntity<?> updateTask(@RequestBody Task task) {
		return ResponseEntity.ok(taskService.updateTask(task.getId(), task));
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Create a new task", description = "This endpoint allows you to create a new task in the task manager.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Task Ok successfully"),
		@ApiResponse(responseCode = "400", description = "Bad Request - Invalid task data")})
	public ResponseEntity<?> getTaskById(@PathVariable Long id) {
		return ResponseEntity.ok(taskService.getTaskById(id));
	}
	
	@GetMapping("/all")
	@Operation(summary = "Get all tasks", description = "This endpoint retrieves all tasks from the task manager.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Tasks retrieved successfully"),
		@ApiResponse(responseCode = "404", description = "No tasks found")})
	public ResponseEntity<?> getAllTasks() {
		return ResponseEntity.ok(taskService.getAllTasks());
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Delete a task", description = "This endpoint allows you to delete a task by its ID.")
	@ApiResponses({
		@ApiResponse(responseCode = "204", description = "Task deleted successfully"),
		@ApiResponse(responseCode = "404", description = "Task not found")})
	public ResponseEntity<?> deleteTask(@PathVariable Long id) {
		taskService.deleteTask(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
