package com.manager.task_manager_api.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.server.ResponseStatusException;

import com.manager.task_manager_api.model.User;
import com.manager.task_manager_api.repository.UsersRepository;
import com.manager.task_manager_api.service.UsersService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UsersServiceImpl implements UsersService {
	
	private final UsersRepository usersRepository;
	
	public UsersServiceImpl(UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}

	@Override
	public User createUser(User user) {
		if (ObjectUtils.isEmpty(user)) {
			log.error("User cannot be null or empty");
			throw new ResponseStatusException (HttpStatus.BAD_REQUEST, "User cannot be null or empty at " + System.currentTimeMillis());
		}
		log.info("Creating user: {}", user.getUsername());
		return usersRepository.save(user);
	}

	@Override
	public User getUserById(String login) {
		if (ObjectUtils.isEmpty(login)) {
			log.error("Login cannot be null or empty");
			throw new ResponseStatusException (HttpStatus.BAD_REQUEST, "Login cannot be null or empty at " + System.currentTimeMillis());
		}
		User userExists = usersRepository.existsById(login);
		if (ObjectUtils.isEmpty(userExists)) {
			log.error("User not found with login: {}", login);
			throw new ResponseStatusException (HttpStatus.NOT_FOUND, "User not found with login: " + login + " at " + System.currentTimeMillis());
		}
		return userExists; // Replace with actual implementation
	}

}
