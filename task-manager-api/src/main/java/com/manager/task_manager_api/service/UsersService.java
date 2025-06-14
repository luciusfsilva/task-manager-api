package com.manager.task_manager_api.service;

import com.manager.task_manager_api.model.User;

public interface UsersService {

	public User createUser(User user);
	public User getUserById(String login);
}
