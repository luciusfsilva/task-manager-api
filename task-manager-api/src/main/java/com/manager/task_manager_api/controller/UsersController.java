package com.manager.task_manager_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manager.task_manager_api.model.User;
import com.manager.task_manager_api.service.UsersService;

@RestController
@RequestMapping("/api/auth")
public class UsersController {
	
	private final UsersService usersService;
	
	public UsersController(UsersService usersService) {
		this.usersService = usersService;
	}

	@PostMapping("/register")
	public ResponseEntity<?> createUser(@RequestBody User user) {
		User createdUser = usersService.createUser(user);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(createdUser);
	}
	
	/*
	@PostMapping("/login")
	public ResponseEntity<?> getUserById(@RequestBody String login, @RequestBody String password) {
		boolean userExist = usersService.login(login, password);
		if (userExist == false) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("User not found with login: " + login);
		}
		return ResponseEntity.ok(userExist);
	}*/
	
}
