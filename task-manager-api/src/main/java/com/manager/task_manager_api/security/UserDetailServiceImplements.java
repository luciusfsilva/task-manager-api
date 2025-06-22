package com.manager.task_manager_api.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.manager.task_manager_api.model.User;
import com.manager.task_manager_api.service.UsersService;

@Service
public class UserDetailServiceImplements implements UserDetailsService {

	private final UsersService usersService;

	public UserDetailServiceImplements(UsersService usersService) {
		this.usersService = usersService;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = usersService.getUserByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new UserAuthenticated(user);
	}

}
