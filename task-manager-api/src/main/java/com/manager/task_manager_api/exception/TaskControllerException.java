package com.manager.task_manager_api.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TaskControllerException {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiError> handleTaskNotFoundException(ResourceNotFoundException ex) {
		ResourceNotFoundException apiError = new ResourceNotFoundException(ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
				new ApiError(apiError.getMessage(), "NOT_FOUND", LocalDateTime.now()));
	}
	
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ApiError> handleBadRequestException(BadRequestException ex) {
		BadRequestException apiError = new BadRequestException(ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
				new ApiError(apiError.getMessage(), "BAD_REQUEST", LocalDateTime.now()));
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiError> handleGenericException(Exception ex) {
		String errorMessage = "An unexpected error occurred: " + ex.getMessage();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
				new ApiError(errorMessage, "INTERNAL_SERVER_ERROR", LocalDateTime.now()));
	}

}
