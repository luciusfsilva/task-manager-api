package com.manager.task_manager_api.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserCriptographPassword {
	
	public static String criptographPassword(String password) {
		if (password == null || password.isEmpty()) {
			throw new IllegalArgumentException("Password cannot be null or empty");
		}
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.encode(password);
	}
	
	public static boolean matchesPassword(String rawPassword, String encodedPassword) {
		if (rawPassword == null || rawPassword.isEmpty() || encodedPassword == null || encodedPassword.isEmpty()) {
			throw new IllegalArgumentException("Raw password and encoded password cannot be null or empty");
		}
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.matches(rawPassword, encodedPassword);
	}

}
