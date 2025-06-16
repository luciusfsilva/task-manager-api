package com.manager.task_manager_api.service.impl;

import java.util.Objects;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.manager.task_manager_api.model.User;
import com.manager.task_manager_api.repository.UsersRepository;
import com.manager.task_manager_api.service.UserDetail;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserDetailServiceImpl implements UserDetail {
	
	private final UsersRepository usersRepository;
	
	public UserDetailServiceImpl(UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}

	@Override
	public String getByPasswordCrypt(String password) {
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		
		if (password != null && !password.isEmpty()) {
			log.info("Encrypting password");
			return bcrypt.encode(password);
		} else {
			return null;
		}
	}

	@Override
	public String getByPasswordDescrypt(String username) {
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		User user = usersRepository.findByUsername(username);
		
		if (user != null && !Objects.isNull(user)) {
			String passwordCrypt = user.getPassword();
			log.info("Decrypting password");
			return bcrypt.matches(passwordCrypt, passwordCrypt) ? passwordCrypt : null;
		} else {
			log.warn("Password not found or empty");
			return null;
		}
	}

	@Override
	public User getByUserName(String userName) {
		User user = usersRepository.findByUsername(userName);
		
		if (user != null && !Objects.isNull(user)) {
			if (user.getUsername().equals(userName)) {
				log.info("Username matches: {}", userName);
			} else {
				log.warn("Username does not match: expected {}, found {}", userName, user.getUsername());
			}
			
			
			return user;
		} else {
			log.warn("User not found with username: {}", userName);
		}
		return null; 
	}

}
